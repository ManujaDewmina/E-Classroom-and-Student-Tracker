package com.example.videomeeting.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.videomeeting.R;
import com.example.videomeeting.utilities.Constants;
import com.example.videomeeting.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import timber.log.Timber;

public class StartScreenStudentActivity extends AppCompatActivity {

    private PreferenceManager preferenceManager;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen_student);

        preferenceManager = new PreferenceManager(getApplicationContext());

        TextView textTitle= findViewById(R.id.textTitle);
        textTitle.setText(String.format(
                "%s %s",
                preferenceManager.getString(Constants.KEY_FIRST_NAME),
                preferenceManager.getString(Constants.KEY_LAST_NAME)
        ));

        findViewById(R.id.textSignOut).setOnClickListener(view -> signOut());
        findViewById(R.id.buttonNotes).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ViewNotesActivity.class)));
        findViewById(R.id.imageNotes).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ViewNotesActivity.class)));
        findViewById(R.id.buttonMeeting).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), OnlineMeetingActivity.class)));
        findViewById(R.id.imageMeeting).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), OnlineMeetingActivity.class)));
        findViewById(R.id.imageAttendance).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),AttendanceActivity.class)));
        findViewById(R.id.buttonAttendance).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), AttendanceActivity.class)));
    }

    private void signOut(){
        Toast.makeText(this, "Signing out...", Toast.LENGTH_SHORT).show();
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference documentReference =
                database.collection(Constants.KEY_COLLECTION_STUDENTS).document(
                        preferenceManager.getString(Constants.KEY_USER_ID)
                );
        HashMap<String ,Object> updates = new HashMap<>();
        updates.put(Constants.KEY_FCM_TOKEN, FieldValue.delete());
        documentReference.update(updates)
                .addOnSuccessListener(unused -> {
                    String userID = preferenceManager.getString(Constants.KEY_USER_ID);
                    databaseReference = FirebaseDatabase.getInstance().getReference("time/"+userID);
                    Date currentTime = Calendar.getInstance().getTime();
                    databaseReference.child("out").setValue(currentTime);
                    preferenceManager.clearPreferences();
                    startActivity(new Intent(getApplicationContext(), SelectUserActivity.class));
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(StartScreenStudentActivity.this, "Unable to sign out", Toast.LENGTH_SHORT).show());

    }

}