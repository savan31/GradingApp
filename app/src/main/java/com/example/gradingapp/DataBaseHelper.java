package com.example.gradingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String dbName = "Grades.db";
    public static final int version = 1;
    public static final String TABLE_NAME = "Student";
    public static final String COL1 = "id";
    public static final String COL2 = "firstName";
    public static final String COL3 = "lastName";
    public static final String COL4 = "course";
    public static final String COL5 = "credits";
    public static final String COL6 = "marks";
    Cursor cursor;

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DataBaseHelper(@Nullable Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creating the Database

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id INTEGER PRIMARY KEY, firstName VARCHAR, lastName VARCHAR, course VARCHAR, credits VARCHAR, marks VARCHAR)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public boolean InsertGrade(StudentModel objStudentModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName", objStudentModel.getFirstName());
        contentValues.put("lastName", objStudentModel.getLastName());
        contentValues.put("course", objStudentModel.getProgramCode());
        contentValues.put("credits", objStudentModel.getCredit());
        contentValues.put("marks", objStudentModel.getMarks());

        long result = db.insert(TABLE_NAME, null, contentValues);


        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor viewData(){
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        if (cursor != null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // Show data by id
    public Cursor viewIdData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME + " where id = "+ id, null);

        if (cursor != null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //Show data by Course name
    public Cursor viewCourseData(String course){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME + " where course = "+ "'"+course+"'", null);

        if (cursor != null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }

}