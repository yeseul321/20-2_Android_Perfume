package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowInfoActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private TextView nameText;
    private TextView brandText;
    private TextView typeText;
    private TextView scentText;
    private TextView sum_star;
    private TextView count;
    private ImageView photoImage;
    private Button showAllButton;
    private Button writeReviewButton;
    private Button prevButton;
    private RatingBar sum_ratingBar;

    private RatingBar ratingBar;
    private TextView username;
    private TextView user_review;
    public static Context context;
    public String path;
    public String name = ((MainActivity)MainActivity.context_main).current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);


        context =this;
        photoImage = (ImageView)findViewById(R.id.iv_photo);
        nameText = (TextView)findViewById(R.id.tv_name);
        brandText = (TextView)findViewById(R.id.tv_brand);
        typeText = (TextView)findViewById(R.id.tv_type);
        scentText = (TextView)findViewById(R.id.tv_scent);
        showAllButton = (Button)findViewById(R.id.showAllButton);
        writeReviewButton = (Button)findViewById(R.id.writeReviewButton);
        prevButton = (Button)findViewById(R.id.prevButton2);
        sum_star = (TextView)findViewById(R.id.sum_star);
        count = (TextView)findViewById(R.id.count);

        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동

        sum_ratingBar = (RatingBar)findViewById(R.id.sum_ratingbar);
        ratingBar = (RatingBar)findViewById(R.id.user1_ratingbar);
        username = (TextView)findViewById(R.id.user);
        user_review=(TextView)findViewById(R.id.user_review);
        findReview();
        databaseReference = database.getReference("향수"); // DB 테이블 연결

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                        perfume info = snapshot.getValue(perfume.class); // 만들어둔 perfume 객체에 데이터를 담는다.
                        if(info.getName().equals(name)){
                            Glide.with(getApplicationContext())
                                    .load(info.getPhoto())
                                    .into(photoImage);
                            nameText.setText(info.getName());
                            brandText.setText(info.getBrand());
                            typeText.setText(info.getType());
                            scentText.setText(info.getScent());
                           count.setText(String.valueOf(info.getReview_count()));
                           sum_star.setText(Float.toString(info.getEstimating()));
                           sum_ratingBar.setRating(info.getEstimating());

                        }

                    }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 디비를 가져오던중 에러 발생 시
                Log.e("ShowInfoActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });
        showAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ShowReviewActivity.class);
                startActivity(intent);
            }
        });
        writeReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ReviewActivity.class);
                startActivity(intent);
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void findReview(){

        database = FirebaseDatabase.getInstance(); //firebase DB와 연동
        databaseReference = database.getReference("향수"); // Firebase 의 DB 테이블과 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //반복문으로 데이터의 list 추출
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    perfume info = snapshot.getValue(perfume.class); // 만들어둔 perfume 객체에 데이터를 담는다.
                    if(info.getName().equals(name)) {

                        path = snapshot.getKey();
                        databaseReference = database.getReference("/향수/"+path+"/review/"); // DB 테이블 연결

                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                                int cnt;
                                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                                { // 반복문으로 데이터 List를 추출해냄
                                    review review = snapshot.getValue(review.class); // 만들어둔 perfume 객체에 데이터를 담는다.
                                    ratingBar.setRating(review.getReview_stars());
                                    username.setText(review.getUserId());
                                    user_review.setText(review.getReview_text());
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //에러가 발생할 경우
                Log.e("ShowInfoActivity", String.valueOf(databaseError.toException()));
            }
        });

    }
}