package com.example.videomeeting.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.videomeeting.R;
import com.example.videomeeting.models.putPDF;
import com.example.videomeeting.utilities.Constants;
import com.example.videomeeting.utilities.PreferenceManager;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

import javax.annotation.Nullable;

public class UploadNotesActivity extends AppCompatActivity {

    private EditText editNote,inputSubjectCode, inputNoteCode,inputNotePassword;
    private MaterialButton buttonUploadPDF;
    private PreferenceManager preferenceManager;

    StorageReference storageReference;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_notes);

        preferenceManager = new PreferenceManager(getApplicationContext());


        TextView textTitle= findViewById(R.id.textTitle);
        textTitle.setText(String.format(
                "%s %s",
                preferenceManager.getString(Constants.KEY_FIRST_NAME),
                preferenceManager.getString(Constants.KEY_LAST_NAME)
        ));

        findViewById(R.id.imageBack).setOnClickListener(view -> onBackPressed());


        inputSubjectCode = findViewById(R.id.inputSubjectCode);
        inputNoteCode = findViewById(R.id.inputNoteCode);
        //inputNotePassword = findViewById(R.id.inputNotePassword);
        editNote  = findViewById(R.id.editNote);
        buttonUploadPDF = findViewById(R.id.buttonUploadPDF);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        buttonUploadPDF.setEnabled(false);

        editNote.setOnClickListener(view -> selectPDF());
    }

    private void selectPDF() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"PDF FILE SELECT"),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            buttonUploadPDF.setEnabled(true);
            editNote.setText(data.getDataString()
                    .substring(data.getDataString().lastIndexOf("/")+1));

            buttonUploadPDF.setOnClickListener(view -> {

                if (inputSubjectCode.getText().toString().trim().isEmpty()) {
                    Toast.makeText(UploadNotesActivity.this, "Enter Subject Code", Toast.LENGTH_SHORT).show();
                } else if (inputNoteCode.getText().toString().trim().isEmpty()) {
                    Toast.makeText(UploadNotesActivity.this, "Enter Note Code", Toast.LENGTH_SHORT).show();
//                } else if (inputNotePassword.getText().toString().trim().isEmpty()) {
//                    Toast.makeText(UploadNotesActivity.this, "Enter Note Password", Toast.LENGTH_SHORT).show();
                } else {
                    uploadPDFFileFirebase(data.getData());
                }
            });
        }
    }


    private void uploadPDFFileFirebase(Uri data) {
        final ProgressDialog progressDialog =new ProgressDialog(this);
        progressDialog.setTitle("File is loading...");
        progressDialog.show();

        StorageReference reference = storageReference.child("uploadPDF"+System.currentTimeMillis()+".pdf");

        reference.putFile(data)
                .addOnSuccessListener(taskSnapshot -> {

                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while(!uriTask.isComplete());
                    Uri uri = uriTask.getResult();

                    putPDF putPDF = new putPDF(editNote.getText().toString(),uri.toString(),inputSubjectCode.getText().toString(),inputNoteCode.getText().toString(),inputNotePassword.getText().toString());
                    databaseReference.child(databaseReference.push().getKey()).setValue(putPDF);

                    FirebaseFirestore database = FirebaseFirestore.getInstance();
                    HashMap<String, Object> user = new HashMap<>();
                    user.put(Constants.KEY_SUBJECT_CODE, inputSubjectCode.getText().toString());
                    user.put(Constants.KEY_NOTE_CODE, inputNoteCode.getText().toString());
                    //user.put(Constants.KEY_NOTE_PASSWORD, inputNotePassword.getText().toString());
                    user.put(Constants.KEY_NOTE_NAME, editNote.getText().toString());
                    user.put(Constants.KEY_NOTE_URL, uri.toString());

                    database.collection(Constants.KEY_COLLECTION_NOTES)
                            .add(user)
                            .addOnSuccessListener(documentReference -> {
                                preferenceManager.putString(Constants.KEY_SUBJECT_CODE , inputSubjectCode.getText().toString());
                                preferenceManager.putString(Constants.KEY_NOTE_CODE, inputNoteCode.getText().toString());
                               // preferenceManager.putString(Constants.KEY_NOTE_PASSWORD, inputNotePassword.getText().toString());
                                preferenceManager.putString(Constants.KEY_NOTE_NAME, editNote.getText().toString());
                                preferenceManager.putString(Constants.KEY_NOTE_URL, uri.toString());
                                Intent intent = new Intent(getApplicationContext(), TeacherNotesActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(UploadNotesActivity.this, "Error : "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                            });

                    Toast.makeText(UploadNotesActivity.this,"File Upload",Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                })
                .addOnProgressListener(snapshot -> {

                    double progress = (100.0* snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                    progressDialog.setMessage("File Uploaded.."+(int) progress+"%");
                });
    }

}