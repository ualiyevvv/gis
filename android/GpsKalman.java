package com.example.myapplication;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.widget.TextView;

import mad.location.manager.lib.Interfaces.LocationServiceInterface;
import mad.location.manager.lib.Services.ServicesHelper;

public class GpsKalman implements LocationServiceInterface {
    Location location; // location
    double latitude; // latitude
    double longitude; // longitude

    private ApiClass api;
    private TextView tvLatitude = null;
    private TextView tvLongitude = null;
    private Entity entity = null;
    Context mContext;

    public GpsKalman(Context context, TextView tvLatitude, TextView tvLongitude) {
        mContext = context;
        api = new ApiClass();
        this.tvLatitude =  tvLatitude;
        this.tvLongitude = tvLongitude;


        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        ServicesHelper.addLocationServiceInterface(this);
    }



    @Override
    public void locationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        displayLocation();

        if (entity != null) {
            entity.setCoords(Double.toString(latitude), Double.toString(longitude));
            entity.setCordsNew(latitude, longitude);

            tvLatitude.setText(entity.toString());
        }

    }

    public void setActivity(Entity activity) {
        this.entity = activity;
    }
    public void removeActivity() {
        api.sendEntity(entity);

        this.entity = null;
    }


    /**
     * Function to get latitude
     * */

    public double getLatitude(){
        if(location != null){
            latitude = location.getLatitude();
        }

        // return latitude
        return latitude;
    }

    /**
     * Function to get longitude
     * */

    public double getLongitude(){
        if(location != null){
            longitude = location.getLongitude();
        }

        // return longitude
        return longitude;
    }

    public void displayLocation() {
        tvLatitude.setText(Double.toString(latitude));
        tvLongitude.setText(Double.toString(longitude));

    }
}
