package com.example.parul.gpacalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    EditText NumberOfSubjectsTxt = (EditText)findViewById(R.id.txt_subjects);
    int noOfSubject = Integer.parseInt(NumberOfSubjectsTxt.getText().toString());
    int gpa =0;

    public void pressOkay(int number){
        for (int i=1; i<=number;i++){
            EditText NumberOfCreditsTxt = (EditText)findViewById(R.id.txt_credits);
            int noOfCerdits = Integer.parseInt(NumberOfCreditsTxt.getText().toString());
            EditText GradeTxt = (EditText)findViewById(R.id.txt_grade);
            String grade = GradeTxt.getText().toString();
            gpa =gpa+calcGpa(noOfCerdits,grade);
        }
    }

    public int calcGpa(int number,String grade){
        int point;
        if(grade.equalsIgnoreCase("S"))
            point = 10;
        else if (grade.equalsIgnoreCase("A"))
            point =9;
        else if (grade.equalsIgnoreCase("B"))
            point = 8;
        else if (grade.equalsIgnoreCase("C"))
            point = 7;
        else if (grade.equalsIgnoreCase("D"))
            point = 6;
        else if (grade.equalsIgnoreCase("E"))
            point = 5;
        else if (grade.equalsIgnoreCase("F"))
            point = 4;
        else point = 3;

        int gpa = number*point;
        return gpa;
    }
}

