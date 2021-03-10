package com.example.test;

import java.util.ArrayList;
import java.util.List;

public class perfume  {
    private String name;
    private String brand;
    private float estimating;
    private String photo;
    private String type;
    private String scent;
    private int review_count;

    public int getReview_count() {
        return review_count;
    }

    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScent() {
        return scent;
    }

    public void setScent(String scent) {
        this.scent = scent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getEstimating() {
        return estimating;
    }

    public void setRating(int rating) {
        this.estimating = rating;
    }

    public String getPhoto() {
        return photo;
    }

    public void setProfile(String profile) {
        this.photo = photo;
    }

}
