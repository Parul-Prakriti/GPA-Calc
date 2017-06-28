package com.example.parul.gpacalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("message");
        TextView txt = (TextView)findViewById(R.id.txt_finalgpa);
        txt.setText(message);
    }



}
