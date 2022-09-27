package com.example.videomeeting.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.videomeeting.R;
import com.example.videomeeting.utilities.Constants;
import com.example.videomeeting.utilities.PreferenceManager;

public class SelectUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        findViewById(R.id.textStudent).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SignInStudentActivity.class)));
        findViewById(R.id.imageStudent).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SignInStudentActivity.class)));
        findViewById(R.id.textTeacher).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SignInTeacherActivity.class)));
        findViewById(R.id.imageTeacher).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SignInTeacherActivity.class)));
    }
}