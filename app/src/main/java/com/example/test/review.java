package com.example.test;

public class review {
    private String userId;
    private String review_text;
    private float review_stars;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReview_text() {
        return review_text;
    }

    public void setReview_text(String review_text) {
        this.review_text = review_text;
    }

    public float getReview_stars() {
        return review_stars;
    }

    public void setReview_stars(float review_stars) {
        this.review_stars = review_stars;
    }
}
