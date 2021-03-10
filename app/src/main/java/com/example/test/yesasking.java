package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.test.R;

public class yesasking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yesasking);

        Button woody = (Button)findViewById(R.id.woody);
        Button citrus = (Button)findViewById(R.id.citrus);
        Button fruity = (Button)findViewById(R.id.fruity);
        Button musk = (Button)findViewById(R.id.musk);
        Button oriental = (Button)findViewById(R.id.oriental);
        Button floral = (Button)findViewById(R.id.floral);

        woody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), FinalAsking.class);

                intent.putExtra("perfume", "우디");

                startActivity(intent);
            }
        });

        citrus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FinalAsking.class);

                intent.putExtra("perfume", "시트러스");

                startActivity(intent);
            }
        });

        fruity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FinalAsking.class);

                intent.putExtra("perfume", "프루티");

                startActivity(intent);
            }
        });

        musk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FinalAsking.class);

                intent.putExtra("perfume", "머스크");

                startActivity(intent);
            }
        });

        oriental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FinalAsking.class);

                intent.putExtra("perfume", "오리엔탈");

                startActivity(intent);
            }
        });

        floral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FinalAsking.class);

                intent.putExtra("perfume", "플로랄");

                startActivity(intent);
            }
        });
    }
}