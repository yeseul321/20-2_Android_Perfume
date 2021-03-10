package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class asking extends AppCompatActivity {

    private Button buttonyesasking;
    private Button buttonnoasking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asking2);

        buttonyesasking = (Button) findViewById(R.id.yesasking);
        buttonyesasking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // yesasking 연결
                Intent intent = new Intent(asking.this, yesasking.class);
                startActivity(intent);
            }
        });

        buttonnoasking = (Button) findViewById(R.id.noasking);
        buttonnoasking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // noasking 연결
                Intent intent = new Intent(asking.this, noasking.class);
                startActivity(intent);
            }
        });
    }
}