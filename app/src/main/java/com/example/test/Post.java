package com.example.test;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Post {
    private String userId;
    private float review_stars;
    private String review_text;

    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Post(String userId,float review_stars, String review_text) {
        this.userId=userId;
        this.review_stars = review_stars;
        this.review_text = review_text;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userId",userId);
        result.put("review_stars", review_stars);
        result.put("review_text", review_text);

        return result;
    }
}
