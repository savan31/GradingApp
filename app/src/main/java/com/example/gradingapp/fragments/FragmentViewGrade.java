package com.example.gradingapp.fragments;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gradingapp.DataBaseHelper;
import com.example.gradingapp.R;
import com.example.gradingapp.StudentModel;
import com.example.gradingapp.recyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentViewGrade extends Fragment {
    private RecyclerView recyclerView;
    private List<StudentModel> arrayList = new ArrayList<>();
    private recyclerViewAdapter adapter;
    DataBaseHelper dataBaseHelper;

    public FragmentViewGrade(){

    }
    @SuppressLint("Range")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_grade,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_ViewGrades);
        dataBaseHelper = new DataBaseHelper(getActivity());
        Cursor mCursor = dataBaseHelper.viewData();
        if (mCursor == null)
        {
            Toast.makeText(getContext(), "No Record", Toast.LENGTH_SHORT).show();
            return view;
        }
        else
        {
            if(mCursor.moveToFirst()){
                do{
                    StudentModel studentModelObj = new StudentModel();
                    int e = Log.e("hi", mCursor.getColumnIndex("id") + "");
                    studentModelObj.setStudentId(mCursor.getInt(mCursor.getColumnIndex("id")));
                    studentModelObj.setFirstName(mCursor.getString(mCursor.getColumnIndex("firstName")));
                    studentModelObj.setLastName(mCursor.getString(mCursor.getColumnIndex("lastName")));
                    studentModelObj.setProgramCode(mCursor.getString(mCursor.getColumnIndex("course")));
                    studentModelObj.setCredit(mCursor.getString(mCursor.getColumnIndex("credits")));
                    studentModelObj.setMarks(mCursor.getString(mCursor.getColumnIndex("marks")));
                    arrayList.add(studentModelObj);
                }
                while (mCursor.moveToNext());
            }
            mCursor.close();

            dataBaseHelper.close();
            bindingAdapter();
            return view;
        }
    }

    //Bind adapter with recycleView
    private void bindingAdapter(){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        adapter = new recyclerViewAdapter(arrayList, getContext());
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
}