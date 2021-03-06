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

        database = FirebaseDatabase.getInstance(); // ?????????????????? ?????????????????? ??????

        sum_ratingBar = (RatingBar)findViewById(R.id.sum_ratingbar);
        ratingBar = (RatingBar)findViewById(R.id.user1_ratingbar);
        username = (TextView)findViewById(R.id.user);
        user_review=(TextView)findViewById(R.id.user_review);
        findReview();
        databaseReference = database.getReference("??????"); // DB ????????? ??????

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // ?????????????????? ????????????????????? ???????????? ???????????? ???
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // ??????????????? ????????? List??? ????????????
                        perfume info = snapshot.getValue(perfume.class); // ???????????? perfume ????????? ???????????? ?????????.
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
                // ????????? ??????????????? ?????? ?????? ???
                Log.e("ShowInfoActivity", String.valueOf(databaseError.toException())); // ????????? ??????
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

        database = FirebaseDatabase.getInstance(); //firebase DB??? ??????
        databaseReference = database.getReference("??????"); // Firebase ??? DB ???????????? ??????
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //??????????????? ???????????? list ??????
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    perfume info = snapshot.getValue(perfume.class); // ???????????? perfume ????????? ???????????? ?????????.
                    if(info.getName().equals(name)) {

                        path = snapshot.getKey();
                        databaseReference = database.getReference("/??????/"+path+"/review/"); // DB ????????? ??????

                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                // ?????????????????? ????????????????????? ???????????? ???????????? ???
                                int cnt;
                                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                                { // ??????????????? ????????? List??? ????????????
                                    review review = snapshot.getValue(review.class); // ???????????? perfume ????????? ???????????? ?????????.
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
                //????????? ????????? ??????
                Log.e("ShowInfoActivity", String.valueOf(databaseError.toException()));
            }
        });

    }
}