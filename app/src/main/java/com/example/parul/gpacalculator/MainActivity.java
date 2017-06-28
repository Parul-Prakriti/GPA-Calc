    package com.example.parul.gpacalculator;

    import android.content.Intent;
    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.LinearLayout;
    import android.widget.TextView;

    import java.util.ArrayList;
    import java.util.List;

    public class MainActivity extends AppCompatActivity {
        private LinearLayout mLayout;
        private EditText mEditCreditText;
        private EditText mEditGradeText;
        private Button mButton;
        public double perSubjectPoint=0;
        public  int totalCredits=0;
        List<String> allCredits = new ArrayList<>();
        List<String> allGrades = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);
               setContentView(R.layout.activity_main);
               mLayout = (LinearLayout) findViewById(R.id.linearLayout);
               mEditCreditText = (EditText) findViewById(R.id.txt_credits);
               mEditGradeText =  (EditText) findViewById(R.id.txt_grade);
               mButton = (Button) findViewById(R.id.button);
               mButton.setOnClickListener(onClick());
               TextView textView = new TextView(this);
        }
        private View.OnClickListener onClick() {
            return new View.OnClickListener() {

                   @Override
                   public void onClick(View v) {

                       mLayout.addView(createNewTextView(mEditCreditText.getText().toString(),mEditGradeText.getText().toString()));
                       allCredits.add(mEditCreditText.getText().toString());
                       allGrades.add(mEditGradeText.getText().toString());
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
            textView.setText("Credit: "+text1+ "\nGrade: "+ text2);
            return textView;
        }


        public double pointPerSubj(int number,String grade){
                    double point;
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
                    else point =0;

                    double x = (double)number*point;
                    return x;
        }

        public void calcGpa(View view){
            int size = allCredits.size();
            String[] stringsGrade = new String[size];
            Integer[] intCredits = new Integer[size];
                    for(int i=0; i < size; i++){
                        stringsGrade[i] = allGrades.get(i);
                        intCredits[i] = Integer.parseInt(allCredits.get(i));
                        totalCredits =totalCredits+intCredits[i];
                        perSubjectPoint = perSubjectPoint+pointPerSubj(intCredits[i],stringsGrade[i]);
                    }
            double gpa = perSubjectPoint/(double)totalCredits;
            String gpaStr = String.format("%.2f",gpa);
            Intent final_gpa =new Intent(MainActivity.this,FinalActivity.class);
            final_gpa.putExtra("message", gpaStr);
            startActivity(final_gpa);
        }
    }

