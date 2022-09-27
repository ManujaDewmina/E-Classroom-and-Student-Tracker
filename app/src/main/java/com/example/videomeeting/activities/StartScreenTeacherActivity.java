package com.example.videomeeting.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.videomeeting.R;
import com.example.videomeeting.utilities.Constants;
import com.example.videomeeting.utilities.PreferenceManager;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class StartScreenTeacherActivity extends AppCompatActivity {

    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen_teacher);

        preferenceManager = new PreferenceManager(getApplicationContext());

        TextView textTitle= findViewById(R.id.textTitle);
        textTitle.setText(String.format(
                "%s %s",
                preferenceManager.getString(Constants.KEY_FIRST_NAME),
                preferenceManager.getString(Constants.KEY_LAST_NAME)
        ));

        findViewById(R.id.textSignOut).setOnClickListener(view -> signOut());

        findViewById(R.id.buttonNote).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), TeacherNotesActivity.class)));
        findViewById(R.id.imageNote).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), TeacherNotesActivity.class)));
        findViewById(R.id.buttonMeeting).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), OnlineMeetingActivity.class)));
        findViewById(R.id.imageMeeting).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), OnlineMeetingActivity.class)));


    }

    private void signOut(){
        Toast.makeText(this, "Signing out...", Toast.LENGTH_SHORT).show();
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference documentReference =
                database.collection(Constants.KEY_COLLECTION_TEACHERS).document(
                        preferenceManager.getString(Constants.KEY_USER_ID)
                );
        HashMap<String ,Object> updates = new HashMap<>();
        updates.put(Constants.KEY_FCM_TOKEN, FieldValue.delete());
        documentReference.update(updates)
                .addOnSuccessListener(unused -> {
                    preferenceManager.clearPreferences();
                    startActivity(new Intent(getApplicationContext(), SelectUserActivity.class));
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(StartScreenTeacherActivity.this, "Unable to sign out", Toast.LENGTH_SHORT).show());

    }

}