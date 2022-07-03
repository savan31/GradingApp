package com.example.gradingapp.fragments;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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

public class FragmentSearch extends Fragment{
    DataBaseHelper dataBaseHelper;
    String string;
    private RecyclerView recyclerView;
    private List<StudentModel> studentList = new ArrayList<>();
    private recyclerViewAdapter adapter;
    EditText id;
    RadioGroup radioGroup;
    TextView coursesTextView;
    Button searchIdButton, searchListButton;

    public FragmentSearch(){

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container,false);

        id = view.findViewById(R.id.editText_ID);
        coursesTextView = view.findViewById(R.id.textView_Courses);
        searchIdButton = view.findViewById(R.id.btn_SearchID);
        searchListButton = view.findViewById(R.id.btn_SearchList);
        coursesTextView.setVisibility(View.GONE);
        recyclerView = view.findViewById(R.id.recycler_ViewGrades);
        id.setVisibility(View.GONE);
        searchIdButton.setVisibility(View.GONE);
        searchListButton.setVisibility(View.GONE);

        ArrayList<String> data = new ArrayList<>();
        data.add("PROG 8480");
        data.add("PROG 8470");
        data.add("PROG 8460");
        data.add("PROG 8450");

        //create object of adaptor
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.course_list, data);

        //set listview
        final ListView listView = (ListView) view.findViewById(R.id.courseListView);
        listView.setVisibility(View.GONE);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //get value from course list view
                Object o = listView.getItemAtPosition(position);
                string = o.toString();

            }

        });

        //Adjusting visibility by radiobutton selection
        radioGroup = view.findViewById(R.id.Search);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton search = (RadioButton) radioGroup.findViewById(checkedId);

                if (search.getId() == R.id.radioBtn_ID)
                {
                    listView.setVisibility(View.GONE);
                    coursesTextView.setVisibility(View.GONE);
                    id.setVisibility(View.VISIBLE);
                    searchIdButton.setVisibility(View.VISIBLE);
                    searchListButton.setVisibility(View.GONE);

                }

                else if (search.getId() == R.id.radioBtn_Code)
                {
                    id.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    coursesTextView.setVisibility(View.VISIBLE);
                    searchIdButton.setVisibility(View.GONE);
                    searchListButton.setVisibility(View.VISIBLE);
                }

            }
        });

        //getting data
        searchIdButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                dataBaseHelper = new DataBaseHelper(getActivity());
                Cursor cursor = dataBaseHelper.viewIdData(id.getText().toString());
                if (cursor == null)
                {
                    Toast.makeText(getContext(), "No Record", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(cursor.moveToFirst()){
                        do{
                         StudentModel studentModelObj = new StudentModel();
                            Log.e("hi",cursor.getColumnIndex("id")+"");
                            studentModelObj.setStudentId(cursor.getInt(cursor.getColumnIndex("id")));
                            studentModelObj.setFirstName(cursor.getString(cursor.getColumnIndex("firstName")));
                            studentModelObj.setLastName(cursor.getString(cursor.getColumnIndex("lastName")));
                            studentModelObj.setProgramCode(cursor.getString(cursor.getColumnIndex("course")));
                            studentModelObj.setCredit(cursor.getString(cursor.getColumnIndex("credits")));
                            studentModelObj.setMarks(cursor.getString(cursor.getColumnIndex("marks")));
                            studentList.add(studentModelObj);
                        }
                        while (cursor.moveToNext());
                    }
                    cursor.close();
                    dataBaseHelper.close();
                    bindingAdapter();

                }

            }
        });

        searchListButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                dataBaseHelper = new DataBaseHelper(getActivity());
                Cursor cursor = dataBaseHelper.viewCourseData(string);
                if (cursor == null)
                {
                    Toast.makeText(getContext(), "No Record", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(cursor.moveToFirst()){
                        do{
                            StudentModel studentModelObj = new StudentModel();
                            Log.e("hi",cursor.getColumnIndex("id")+"");
                            studentModelObj.setStudentId(cursor.getInt(cursor.getColumnIndex("id")));
                            studentModelObj.setFirstName(cursor.getString(cursor.getColumnIndex("firstName")));
                            studentModelObj.setLastName(cursor.getString(cursor.getColumnIndex("lastName")));
                            studentModelObj.setProgramCode(cursor.getString(cursor.getColumnIndex("course")));
                            studentModelObj.setCredit(cursor.getString(cursor.getColumnIndex("credits")));
                            studentModelObj.setMarks(cursor.getString(cursor.getColumnIndex("marks")));
                            studentList.add(studentModelObj);
                        }
                        while (cursor.moveToNext());
                    }
                    cursor.close();
                    dataBaseHelper.close();
                    bindingAdapter();
                }
            }
        });
        return view;
    }

    private void bindingAdapter(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new recyclerViewAdapter(studentList, getContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}