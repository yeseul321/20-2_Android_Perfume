package com.example.test;

import com.google.android.gms.maps.model.Marker;

public class placeList {
    private String place_name;
    private String place_address;
    private Marker place_marker;
    private double longtitude;
    private double lattitude;
    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public Marker getPlace_marker() { return place_marker; }

    public void setPlace_marker(Marker place_marker) { this.place_marker = place_marker; }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getPlace_address() {
        return place_address;
    }

    public void setPlace_address(String place_address) {
        this.place_address = place_address;
    }
}
