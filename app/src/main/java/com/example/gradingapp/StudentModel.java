package com.example.gradingapp;

public class StudentModel {
    private int studentId;
    private String firstName,lastName,programCode,credit,marks;

    public StudentModel(){

    }
    public StudentModel(int id, String FName, String LName, String code, String credit, String marks)
    {
        this.studentId = id;
        this.firstName = FName;
        this.lastName = LName;
        this.programCode = code;
        this.credit = credit;
        this.marks = marks;
    }

    public void setStudentId(int id)
    {
        this.studentId = id;
    }
    public void setFirstName(String FName)
    {
        this.firstName = FName;
    }
    public void setLastName(String LName)
    {
        this.lastName = LName;
    }
    public void setProgramCode(String code)
    {
        this.programCode = code;
    }
    public void setCredit(String credit) { this.credit = credit; }
    public void setMarks(String marks) { this.marks = marks; }

    public int getStudentId()
    {
        return studentId;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public String getProgramCode() { return programCode; }
    public String getCredit() { return credit; }
    public String getMarks()
    {
        return marks;
    }
}