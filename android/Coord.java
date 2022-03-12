package com.example.myapplication;

public class Coord {
    private String lat;
    private String lon;

    public Coord(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public String getLon() {
        return lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }
}
