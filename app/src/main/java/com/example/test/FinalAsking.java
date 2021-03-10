package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.RecommendActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class FinalAsking extends AppCompatActivity {

    Spinner spinner1;
    Spinner spinner2;
    public String s1;
    public String s2;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_asking);


        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        Button button = (Button) findViewById(R.id.spinnerbutton);

        Intent intent = getIntent();
        String perfume = intent.getStringExtra("perfume");

        if (perfume.equals("우디")) {
            ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.우디, android.R.layout.simple_spinner_dropdown_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner1.setAdapter(arrayAdapter);
            spinner2.setAdapter(arrayAdapter);
            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position) + "(이)가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                    s1 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position) + "(이)가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                    s2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } else if (perfume.equals("플로랄")) {
            ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.플로랄, android.R.layout.simple_spinner_dropdown_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner1.setAdapter(arrayAdapter);
            spinner2.setAdapter(arrayAdapter);
            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position) + "(이)가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                    s1 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position) + "(이)가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                    s2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (perfume.equals("오리엔탈")) {
            ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.오리엔탈, android.R.layout.simple_spinner_dropdown_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner1.setAdapter(arrayAdapter);
            spinner2.setAdapter(arrayAdapter);
            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position) + "(이)가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                    s1 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position) + "(이)가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                    s2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (perfume.equals("프루티")) {
            ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.프루티, android.R.layout.simple_spinner_dropdown_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner1.setAdapter(arrayAdapter);
            spinner2.setAdapter(arrayAdapter);
            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position) + "(이)가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                    s1 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position) + "(이)가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                    s2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (perfume.equals("시트러스")) {
            ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.시트러스, android.R.layout.simple_spinner_dropdown_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner1.setAdapter(arrayAdapter);
            spinner2.setAdapter(arrayAdapter);
            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position) + "(이)가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                    s1 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position) + "(이)가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                    s2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (perfume.equals("머스크")) {
            ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.머스크, android.R.layout.simple_spinner_dropdown_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner1.setAdapter(arrayAdapter);
            spinner2.setAdapter(arrayAdapter);
            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position) + "(이)가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                    s1 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position) + "(이)가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                    s2 = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String perfume = intent.getStringExtra("perfume");

                intent = new Intent(FinalAsking.this, RecommendActivity.class);
                intent.putExtra("perfume",perfume);
                intent.putExtra("s1",s1);
                intent.putExtra("s2",s2);
                startActivity(intent);
            }
        });


    }
}