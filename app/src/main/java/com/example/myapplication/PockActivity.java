package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PockActivity extends AppCompatActivity {
    private TextView nameTe;
    private TextView num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pock);
        String name = getIntent().getStringExtra("name");
        int number = getIntent().getIntExtra("number",0);
        nameTe = findViewById(R.id.cc);
        num = findViewById(R.id.ee);
        nameTe.setText(name);
        num .setText(Integer.toString(number));
    }
}