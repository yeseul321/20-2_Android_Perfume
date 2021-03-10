package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ReviewActivity extends AppCompatActivity implements View.OnClickListener {

   private RatingBar ratingBar;
   private EditText editText;
   private TextView textView;
   private Button submit_button;
   private Button cancel_button;
   private DatabaseReference databaseReference;
   private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
   private DatabaseReference databaseReference2;
   int review_count=0;
   float total_star=0;
   String path = ((ShowInfoActivity)ShowInfoActivity.context).path;
   private String userId;
   private String review_text;
   private float review_stars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //리뷰 액티비티 구현
        setContentView(R.layout.activity_review);
        //참조
        textView = (TextView) findViewById(R.id.review_textview);
        ratingBar = (RatingBar) findViewById(R.id.rating_bar);
        editText = (EditText) findViewById(R.id.edit_review);
        cancel_button = (Button) findViewById(R.id.cancel_button);
        cancel_button.setOnClickListener((View.OnClickListener) this);
        submit_button = (Button) findViewById(R.id.submit_button);
        submit_button.setOnClickListener((View.OnClickListener) this);

        //버튼 클릭 이벤트 구현
        submit_button.setOnClickListener(this);
        cancel_button.setOnClickListener(this);

    }

    public void postFirebaseDatabase(boolean add){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Map<String,Object> childUpdates = new HashMap<>();
        Map<String,Object> postValues = null;

        if(add){
            Post post = new Post(userId,review_stars, review_text);
            postValues = post.toMap();
        }

        childUpdates.put("/향수/"+path+"/review/"+userId,postValues);
        databaseReference.updateChildren(childUpdates);

    }

    @Override
    public void onClick(View v)
    {
        if (v == submit_button) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            userId = user.getDisplayName();
            review_text = editText.getText().toString();
            review_stars=ratingBar.getRating();
            postFirebaseDatabase(true);
            count();
            finish();

        }
        else if (v == cancel_button) {
            finish();
        }
    }

    public void count(){
        databaseReference2 = firebaseDatabase.getReference("/향수/"+path+"/review/"); // Firebase 의 DB 테이블과 연결
        databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //반복문으로 데이터의 list 추출
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    review info = snapshot.getValue(review.class); // 만들어둔 review 객체에 데이터를 담는다.
                    review_count++;
                    total_star+=info.getReview_stars();
                }
                databaseReference2 = firebaseDatabase.getReference("/향수/"+path+"/");
                databaseReference2.child("review_count").setValue(review_count);
                float result = total_star/review_count;
                databaseReference2.child("estimating").setValue(Math.round(result*100)/100.0);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //에러가 발생할 경우
                Log.e("ShowReviewActivity", String.valueOf(databaseError.toException()));
            }
        });

    }

}
