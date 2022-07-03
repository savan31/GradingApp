package com.example.gradingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class recyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<StudentModel> mList;
    public  recyclerViewAdapter(List<StudentModel> list, Context context){
        super();
        mList = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView firstName;
        public TextView lastName;
        public TextView course;
        public TextView credit;
        public TextView marks;

        //view Holder
        public ViewHolder(View itemView){
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.txt_ID);
            firstName = (TextView) itemView.findViewById(R.id.txt_FirstName);
            lastName = (TextView) itemView.findViewById(R.id.txt_LastName);
            course = (TextView) itemView.findViewById(R.id.txt_Course);
            credit = (TextView) itemView.findViewById(R.id.txt_Credits);
            marks = (TextView) itemView.findViewById(R.id.txt_Marks);

        }
    }
    //Recyclerview Holder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewdata, parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
       StudentModel studentModelAdapter = mList.get(position);

        ((ViewHolder) viewHolder).id.setText(""+ studentModelAdapter.getStudentId());

        ((ViewHolder) viewHolder).firstName.setText(studentModelAdapter.getFirstName());
        ((ViewHolder) viewHolder).lastName.setText(studentModelAdapter.getLastName());
        ((ViewHolder) viewHolder).course.setText(studentModelAdapter.getProgramCode());
        ((ViewHolder) viewHolder).credit.setText(studentModelAdapter.getCredit());
        ((ViewHolder) viewHolder).marks.setText(studentModelAdapter.getMarks());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}