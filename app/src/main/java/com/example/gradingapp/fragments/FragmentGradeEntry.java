package com.example.gradingapp.fragments;

import android.os.Bundle;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gradingapp.DataBaseHelper;
import com.example.gradingapp.R;
import com.example.gradingapp.StudentModel;

import java.util.ArrayList;

public class FragmentGradeEntry extends Fragment {
    EditText firstNameEditText, lastNameEditText, marksEditText;
    Button submitButton;
    DataBaseHelper dataBaseHelper;
    ListView courseListView;
    RadioGroup creditRadioGroup;
    Boolean insertStat;
    String string,creditsString;
    View view;

    public FragmentGradeEntry(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.fragement_grade_entry, container,false);

        //binding with layout

        firstNameEditText = view.findViewById(R.id.firstNameEditText);
        lastNameEditText = view.findViewById(R.id.lastNameEditText);
        courseListView = view.findViewById(R.id.courseListView);
        creditRadioGroup = view.findViewById(R.id.Credit);
        submitButton = view.findViewById(R.id.submitButton);
        marksEditText = view.findViewById(R.id.marksEditText);


        setCourseList();
        onClickEvents();
        return view;
    }

    //Set list view for course
    void setCourseList(){
        //Array List is created for courses
        ArrayList<String> arrayListData = new ArrayList<>();
        arrayListData.add("PROG 8480");
        arrayListData.add("PROG 8470");
        arrayListData.add("PROG 8460");
        arrayListData.add("PROG 8450");

        //set array adapter for course list
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.course_list, arrayListData);

        final ListView listView = (ListView) view.findViewById(R.id.courseListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Object o = listView.getItemAtPosition(position);
                string = o.toString();

                Toast.makeText(getContext(), "Course: " + string,Toast.LENGTH_SHORT).show();
            }

        });
    }
    //All on click events
    void onClickEvents(){
        //Creating radioGroup for credits and use on checked change listner for update
        creditRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton credit = (RadioButton) radioGroup.findViewById(checkedId);

                if (credit.getId() == R.id.radioBtn_Credit1)
                {
                    creditsString = "10";
                }
                else if (credit.getId() == R.id.radioBtn_Credit2)
                {
                    creditsString = "20";
                }
                else if (credit.getId() == R.id.radioBtn_Credit3)
                {
                    creditsString = "30";
                }
                else if (credit.getId() == R.id.radioBtn_Credit4)
                {
                    creditsString = "50";
                }

                Toast.makeText(getContext(), "Credit: " + creditsString,Toast.LENGTH_SHORT).show();
            }

        });
        dataBaseHelper = new DataBaseHelper(getActivity());

        //Submit button for save data
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int studentId = 0;
                StudentModel StudentModel = new StudentModel(studentId, firstNameEditText.getText().toString(), lastNameEditText.getText().toString(), string, creditsString, marksEditText.getText().toString());
                insertStat = dataBaseHelper.InsertGrade(StudentModel);

                if (insertStat)
                {
                    Toast.makeText(getActivity(),"Data Added Successfully", Toast.LENGTH_SHORT).show();
                }

                else if (!insertStat)
                {
                    Toast.makeText(getActivity(),"Data Not Added Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}