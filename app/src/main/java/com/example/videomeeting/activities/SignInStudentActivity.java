package com.example.videomeeting.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.videomeeting.R;
import com.example.videomeeting.models.User;
import com.example.videomeeting.models.putPDF;
import com.example.videomeeting.utilities.Constants;
import com.example.videomeeting.utilities.PreferenceManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;

public class SignInStudentActivity extends AppCompatActivity {

    private EditText inputEmail , inputPassword;
    private MaterialButton buttonSignIn;
    private ProgressBar signInProgressbar;
    private PreferenceManager preferenceManager;

    FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_student);

          preferenceManager = new PreferenceManager(getApplicationContext());

//        if (preferenceManager.getBoolean(Constants.KEY_IS_SIGNED_IN)){
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(intent);
//            finish();
//        }

        findViewById(R.id.imageBack).setOnClickListener(view -> onBackPressed());
        findViewById(R.id.textSignUp).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SignUpStudentActivity.class)));

        mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        //databaseReference = FirebaseDatabase.getInstance().getReference("student_log_in");
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        buttonSignIn = findViewById(R.id.buttonSignIn);
        signInProgressbar = findViewById(R.id.signInProgressBar);

        buttonSignIn.setOnClickListener(view -> {
           if(inputEmail.getText().toString().trim().isEmpty()){
               Toast.makeText(SignInStudentActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
           }else if(!Patterns.EMAIL_ADDRESS.matcher(inputEmail.getText().toString()).matches()){
               Toast.makeText(SignInStudentActivity.this, "Enter valid Email", Toast.LENGTH_SHORT).show();
           }else if (inputPassword.getText().toString().trim().isEmpty()){
               Toast.makeText(SignInStudentActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
           } else{
               signIn();
           }
        });
    }

    private void signIn(){
        buttonSignIn.setVisibility(View.INVISIBLE);
        signInProgressbar.setVisibility(View.VISIBLE);

        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                if(mAuth.getCurrentUser().isEmailVerified()){

                    database.collection(Constants.KEY_COLLECTION_STUDENTS)
                            .whereEqualTo(Constants.KEY_EMAIL, inputEmail.getText().toString())
                            .whereEqualTo(Constants.KEY_PASSWORD, inputPassword.getText().toString())
                            .get()
                            .addOnCompleteListener(task2 -> {
                                if(task2.isSuccessful() && task2.getResult().getDocuments().size()>0){
                                    DocumentSnapshot documentSnapshot = task2.getResult().getDocuments().get(0);
                                    preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN,true);
                                    preferenceManager.putString(Constants.KEY_USER_ID, documentSnapshot.getId());
                                    preferenceManager.putString(Constants.KEY_FIRST_NAME , documentSnapshot.getString(Constants.KEY_FIRST_NAME));
                                    preferenceManager.putString(Constants.KEY_LAST_NAME, documentSnapshot.getString(Constants.KEY_LAST_NAME));
                                    preferenceManager.putString(Constants.KEY_ID_NUMBER, documentSnapshot.getString(Constants.KEY_ID_NUMBER));
                                    preferenceManager.putString(Constants.KEY_EMAIL, documentSnapshot.getString(Constants.KEY_EMAIL));
                                    Toast.makeText(SignInStudentActivity.this, "Logged in Successfully ", Toast.LENGTH_SHORT).show();

                                    String userID = preferenceManager.getString(Constants.KEY_USER_ID);
                                    databaseReference = FirebaseDatabase.getInstance().getReference("time/"+userID);
                                    Date currentTime = Calendar.getInstance().getTime();

                                    databaseReference.child("in").setValue(currentTime);

                                    Intent intent = new Intent(getApplicationContext(), StartScreenStudentActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                } else {
                                    signInProgressbar.setVisibility(View.INVISIBLE);
                                    buttonSignIn.setVisibility(View.VISIBLE);
                                    Toast.makeText(SignInStudentActivity.this, "Error : No Teacher Access", Toast.LENGTH_SHORT).show();
                                }
                            });
                }else{
                    signInProgressbar.setVisibility(View.INVISIBLE);
                    buttonSignIn.setVisibility(View.VISIBLE);
                    Toast.makeText(SignInStudentActivity.this, "Please Verify Your Email", Toast.LENGTH_SHORT).show();
                }

                }else{
                    signInProgressbar.setVisibility(View.INVISIBLE);
                    buttonSignIn.setVisibility(View.VISIBLE);
                    Toast.makeText(SignInStudentActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();

                }


        });



    }
}