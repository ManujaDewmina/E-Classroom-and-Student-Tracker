package com.example.videomeeting.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videomeeting.R;
import com.example.videomeeting.listeners.RecyclerViewInterface;
import com.example.videomeeting.models.putPDF;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    //private  final RecyclerViewInterface recyclerViewInterface;
    Context context;
    List<putPDF> noteList;
    public noteClickListener noteClickListener;

    public interface noteClickListener{
        void selectedNote(putPDF putPDF);
    }

    public void  setFilteredList(List<putPDF> filteredList){
        this.noteList = filteredList;
        notifyDataSetChanged();
    }
    public NoteAdapter(Context context, ArrayList<putPDF> noteList ,noteClickListener noteClickListener) {
        this.context = context;
        this.noteList = noteList;
        this.noteClickListener = noteClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_notes,parent,false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        putPDF putPDF = noteList.get(position);
        holder.name.setText(putPDF.getName());
        holder.courseCode.setText(putPDF.getCourseCode());
        holder.noteCode.setText(putPDF.getNoteCode());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteClickListener.selectedNote(putPDF);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView name,courseCode,noteCode;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.noteName);
            courseCode = itemView.findViewById(R.id.subjectCode);
            noteCode = itemView.findViewById(R.id.noteCode);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(recyclerViewInterface != null){
//                        int pos = getAdapterPosition();
//                        if(pos!= RecyclerView.NO_POSITION){
//                            recyclerViewInterface.onItemClick(pos);
//                        }
//                    }
//                }
//            });
        }
    }
}
