    package com.example.parul.gpacalculator;

    import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

    public class MainActivity extends AppCompatActivity {
        private LinearLayout mLayout;
        private EditText mEditCreditText;
        private EditText mEditGradeText;
        private Button mButton, rButton;

        public String gpaStr;
        List<String> allCredits = new ArrayList<>();
        List<String> allGrades = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mLayout = (LinearLayout) findViewById(R.id.display);
            mEditCreditText = (EditText) findViewById(R.id.txt_credits);
            mEditGradeText =  (EditText) findViewById(R.id.txt_grade);
            mButton = (Button) findViewById(R.id.button);
            rButton = (Button) findViewById(R.id.btn_reset) ;
            mButton.setOnClickListener(onClick());
            rButton.setOnClickListener(onClickreset());
        }
        private View.OnClickListener onClick() {
            return new View.OnClickListener() {

                   @Override
                   public void onClick(View v) {

                       mLayout.addView(createNewTextView(mEditCreditText.getText().toString(),mEditGradeText.getText().toString()));
                       allCredits.add(mEditCreditText.getText().toString());
                       allGrades.add(mEditGradeText.getText().toString());
                       mEditCreditText.setText("");
                       mEditGradeText.setText("");
                        }
                    };
        }
        private TextView createNewTextView(String text1,String text2) {
            final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            final TextView textView = new TextView(this);
            textView.setTextSize(16);
            textView.setTextColor(getResources().getColor(android.R.color.black));
            textView.setPadding(8,1,8,1);
            textView.setLayoutParams(lparams);
            textView.setText("Credit: "+text1+ "\t\t\t"+"Grade: "+ text2);
            return textView;
        }


        public double pointPerSubj(int number,String grade){
            double point=0;
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
            else if (grade.equalsIgnoreCase("F")||grade.equalsIgnoreCase("N"))
                point=0;
            else{
                Toast.makeText(this,"Invalid Input.", Toast.LENGTH_LONG).show();
                onClickreset();
            }


                    double x = (double)number*point;
                    return x;
        }

        public void calcGpa(View view){
            int size = allCredits.size();
            double perSubjectPoint=0;
            int totalCredits=0;
            double gpa ;

            String[] stringsGrade = new String[size];
            Integer[] intCredits = new Integer[size];
                    for(int i=0; i < size; i++){
                        stringsGrade[i] = allGrades.get(i);
                        intCredits[i] = Integer.parseInt(allCredits.get(i));
                        totalCredits =totalCredits+intCredits[i];
                        perSubjectPoint = perSubjectPoint+pointPerSubj(intCredits[i],stringsGrade[i]);
                    }
            if (totalCredits==0)
                Toast.makeText(this,"Invalid Inputs", Toast.LENGTH_SHORT).show();
            else {
                gpa = perSubjectPoint / (double) totalCredits;
                gpaStr = String.format("%.2f", gpa);
                Intent final_gpa = new Intent(MainActivity.this, FinalActivity.class);
                final_gpa.putExtra("message", gpaStr);
                startActivity(final_gpa);
            }
        }
        private View.OnClickListener onClickreset(){
            return new View.OnClickListener(){
                public void onClick(View v){
                    allCredits.clear();
                    allGrades.clear();
                    mLayout.removeAllViews();
                    mEditCreditText.setText("");
                    mEditGradeText.setText("");


                }
            };
        }
    }