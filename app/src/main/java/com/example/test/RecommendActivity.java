package com.example.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.test.R;
import com.example.test.RecommendAdapter;
import com.example.test.perfume;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;

public class RecommendActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Button home_button;
    public Button map_button;
    private RecommendAdapter recommendAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TextView textView;

    ArrayList<perfume> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        home_button = (Button)findViewById(R.id.homeButton);
        map_button = (Button)findViewById(R.id.mapButton);
        textView = (TextView)findViewById(R.id.recommend_textView);
        //버튼 연결
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GpsActivity.class);
                startActivity(intent);
            }
        });
        //리사이클러뷰 설정
        recyclerView = (RecyclerView)findViewById(R.id.recycler_recommend);
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존 성능 강화
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);


        //파이어베이스 데이터베이스 연결
        firebaseDatabase = FirebaseDatabase.getInstance(); //firebase DB와 연동
        databaseReference = firebaseDatabase.getReference("향수");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {

                Intent intent = getIntent();
                String perfume = intent.getStringExtra("perfume");
                String s1 = intent.getStringExtra("s1");
                String s2 = intent.getStringExtra("s2");

                arrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    perfume info = dataSnapshot.getValue(perfume.class);
                    if(info.getType().equals(perfume))
                    {
                        int cnt=0;
                        String[] list = info.getScent().split(",");
                        for(int i=0; i<list.length; i++)
                        {
                            String test = list[i].trim();
                            if(test.equals(s1)||test.equals(s2))
                                cnt++;
                        }
                        if(cnt!=0)
                            arrayList.add(info);
                    }
                }

                Collections.sort(arrayList,new Descending()); //별점 순위 대로 출력
                recommendAdapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recommendAdapter = new RecommendAdapter(arrayList, getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(recommendAdapter); //어댑터 설정 완료


    }
    //별점순 정렬
    class Descending implements Comparator<perfume> {
        @Override
        public int compare(perfume p, perfume q) {
            String a=String.valueOf(p.getEstimating());
            String b=String.valueOf(q.getEstimating());
            return b.compareTo(a);
        }

    }
}
