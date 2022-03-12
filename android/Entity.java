package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class Entity {
//    private String caption;
//    private Date date;
    @SerializedName("lat")
    private String caption;
    @SerializedName("lon")
    private String test = "";
    private ArrayList<Coord> coords;
    private ArrayList<ArrayList<Double>> testcoords;



    public Entity(String caption) {
        this.caption = caption;
        coords = new ArrayList<>();
        testcoords = new ArrayList<>();
    }

    public void setTest(String test) {
        this.test = test;
    }

    public void setCordsNew(Double lat, Double lon) {
        ArrayList<Double> testcoord = new ArrayList<Double>();
        testcoord.add(lat);
        testcoord.add(lon);
        testcoords.add(testcoord);

    }

    public void setCoords(String lat, String lon) {
        Coord coord = new Coord(lat, lon);
        coords.add(coord);
    }

    public ArrayList<ArrayList<Double>> getCoords() {
        return testcoords;
    }


    @Override
    public String toString() {
        return "Entity{" +
                "caption='" + caption + '\'' +
                ", coords=" + coords +
                '}';
    }
}
