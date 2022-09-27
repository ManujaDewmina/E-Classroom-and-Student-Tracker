package com.example.videomeeting.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videomeeting.adapters.NoteAdapter;
import com.example.videomeeting.R;
import com.example.videomeeting.listeners.RecyclerViewInterface;
import com.example.videomeeting.models.putPDF;
import com.example.videomeeting.utilities.Constants;
import com.example.videomeeting.utilities.PreferenceManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewNotesActivity extends AppCompatActivity implements NoteAdapter.noteClickListener{

    private PreferenceManager preferenceManager;

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    NoteAdapter noteAdapter;
    ArrayList<putPDF> noteList;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        preferenceManager = new PreferenceManager(getApplicationContext());

        TextView textTitle = findViewById(R.id.textTitle);
        textTitle.setText(String.format(
                "%s %s",
                preferenceManager.getString(Constants.KEY_FIRST_NAME),
                preferenceManager.getString(Constants.KEY_LAST_NAME)

        ));

        findViewById(R.id.imageBack).setOnClickListener(view -> onBackPressed());


        noteList = new ArrayList<>();
        noteAdapter = new NoteAdapter(this,noteList,this::selectedNote);
        recyclerView.setAdapter(noteAdapter);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    putPDF putPDF = dataSnapshot.getValue(com.example.videomeeting.models.putPDF.class);
                    noteList.add(putPDF);
                }
                noteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void filterList(String Text) {

        List<putPDF> filteredList = new ArrayList<>();
        for( putPDF putPDF : noteList){
            if( putPDF.getCourseCode().toLowerCase().contains(Text.toLowerCase())){
                filteredList.add(putPDF);
            }
            if(filteredList.isEmpty()){
                //Toast.makeText(this, "No Notes Found", Toast.LENGTH_SHORT).show();
            }
            else{
                noteAdapter.setFilteredList(filteredList);
            }
        }
    }


    @Override
    public void selectedNote(putPDF putPDF) {
        Toast.makeText(this, "Selected Note : "+putPDF.getCourseCode(), Toast.LENGTH_SHORT).show();
        gotoUrl(putPDF.url);
    }

    private void gotoUrl(String url) {
//        Intent intent = new Intent(getApplicationContext(), PdfUnlockActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}