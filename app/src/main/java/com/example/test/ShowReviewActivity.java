package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowReviewActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private ReviewAdapter adapter;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private RecyclerView.LayoutManager layoutManager;
    private Button button;

    private ArrayList<review> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_review);

        String path = ((ShowInfoActivity)ShowInfoActivity.context).path;

        //향수 리스트 출력 구현
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView2); // id 연결
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존 성능 강화
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        //파이어베이스 데이터베이스 연결
        firebaseDatabase = FirebaseDatabase.getInstance(); //firebase DB와 연동
        databaseReference = firebaseDatabase.getReference("/향수/"+path+"/review/"); // Firebase 의 DB 테이블과 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Firebase 의 DB를 받아오는 곳
                arrayList.clear(); // 기존 배열리스트를 초기화
                //반복문으로 데이터의 list 추출
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    review info = snapshot.getValue(review.class); // 만들어둔 review 객체에 데이터를 담는다.
                    arrayList.add(info); // 담은 데이터를 배열리스트에 넣고, 리사이클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //에러가 발생할 경우
                Log.e("ShowReviewActivity", String.valueOf(databaseError.toException()));
            }
        });
        adapter = new ReviewAdapter(arrayList, getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        //버튼 구현
        button = (Button)findViewById(R.id.prevButton);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}