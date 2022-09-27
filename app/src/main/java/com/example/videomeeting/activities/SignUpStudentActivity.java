package com.example.videomeeting.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.videomeeting.R;
import com.example.videomeeting.utilities.Constants;
import com.example.videomeeting.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SignUpStudentActivity extends AppCompatActivity {

    private EditText inputFirstName, inputLastName, inputIDNumber, inputEmail, inputPassword, inputConfirmPassword;
    private MaterialButton buttonSignUp;
    private ProgressBar signUpProgressBar;
    private PreferenceManager preferenceManager;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_student);

        preferenceManager = new PreferenceManager(getApplicationContext());

        findViewById(R.id.imageBack).setOnClickListener(view -> onBackPressed());
        findViewById(R.id.textSignIn).setOnClickListener(view -> onBackPressed());

        inputFirstName = findViewById(R.id.inputFirstName);
        inputLastName = findViewById(R.id.inputLastName);
        inputIDNumber = findViewById(R.id.inputIDNumber);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        signUpProgressBar = findViewById(R.id.signUpProgressBar);

        mAuth = FirebaseAuth.getInstance();

        buttonSignUp.setOnClickListener(view -> {
            if (inputFirstName.getText().toString().trim().isEmpty()) {
                Toast.makeText(SignUpStudentActivity.this, "Enter First name", Toast.LENGTH_SHORT).show();
            } else if (inputLastName.getText().toString().trim().isEmpty()) {
                Toast.makeText(SignUpStudentActivity.this, "Enter Last name", Toast.LENGTH_SHORT).show();
            } else if (inputIDNumber.getText().toString().trim().isEmpty()) {
                Toast.makeText(SignUpStudentActivity.this, "Enter ID Number", Toast.LENGTH_SHORT).show();
            } else if (inputEmail.getText().toString().trim().isEmpty()) {
                Toast.makeText(SignUpStudentActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
            } else if(!Patterns.EMAIL_ADDRESS.matcher(inputEmail.getText().toString()).matches()){
                Toast.makeText(SignUpStudentActivity.this, "Enter valid Email", Toast.LENGTH_SHORT).show();
            } else if(inputPassword.getText().toString().trim().isEmpty()) {
                Toast.makeText(SignUpStudentActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
            } else if(inputConfirmPassword.getText().toString().trim().isEmpty()) {
                Toast.makeText(SignUpStudentActivity.this, "Confirm your password", Toast.LENGTH_SHORT).show();
            }else if(!inputPassword.getText().toString().equals(inputConfirmPassword.getText().toString())){
                Toast.makeText(SignUpStudentActivity.this, "Password and Confirm Password must be same", Toast.LENGTH_SHORT).show();
            }else{
                signUp();
            }
        });
    }

    private void signUp(){
        buttonSignUp.setVisibility(View.INVISIBLE);
        signUpProgressBar.setVisibility(View.VISIBLE);

        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser fUser= mAuth.getCurrentUser();
                    fUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(SignUpStudentActivity.this, "User Created &Verification email has been sent.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUpStudentActivity.this, "Error : "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    FirebaseFirestore database = FirebaseFirestore.getInstance();
                    HashMap<String, Object> user = new HashMap<>();
                    user.put(Constants.KEY_FIRST_NAME, inputFirstName.getText().toString());
                    user.put(Constants.KEY_LAST_NAME, inputLastName.getText().toString());
                    user.put(Constants.KEY_ID_NUMBER, inputIDNumber.getText().toString());
                    user.put(Constants.KEY_EMAIL, inputEmail.getText().toString());
                    user.put(Constants.KEY_PASSWORD, inputPassword.getText().toString());

                    database.collection(Constants.KEY_COLLECTION_STUDENTS)
                            .add(user)
                            .addOnSuccessListener(documentReference -> {

                                Intent intent = new Intent(getApplicationContext(), SignInStudentActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            })
                            .addOnFailureListener(e -> {
                                signUpProgressBar.setVisibility(View.INVISIBLE);
                                buttonSignUp.setVisibility(View.VISIBLE);
                                Toast.makeText(SignUpStudentActivity.this, "Error : "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                }else{
                    signUpProgressBar.setVisibility(View.INVISIBLE);
                    buttonSignUp.setVisibility(View.VISIBLE);
                    Toast.makeText(SignUpStudentActivity.this, "Error : Used email address", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}