package com.example.server.measurements;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class Measurements {

    private String TAG= "MainActivity";
    private TelephonyManager telephonyManager;
    private LocationManager locationManager;
    private Context context;

    public Measurements(TelephonyManager telephonyManager, LocationManager locationManager, Context context) {
        this.telephonyManager = telephonyManager;
        this.locationManager = locationManager;
        this.context = context;
    }

    public String getPhoneInfo(){
        SignalStrength info = telephonyManager.getSignalStrength();
        return info.toString();
    }

    public double getLatitude(){
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "nope ");
        }
        Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Log.d(TAG, "onCreate: "+ loc.toString());

        return loc.getLatitude();
    }

    public List<Location> getLatitudeByGooglePlay(){
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        List<Location> locList = new ArrayList<Location>();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();

            locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null){
                        Log.d(TAG, "onSuccess: " + location.toString());
                        locList.add(location);
                    }
                    else{
                        Log.d(TAG, "onSuccess: Location was null");
                    }
                }
            });

            locationTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "on failure: "+ e.getLocalizedMessage());
                }
            });
        }
        return locList;
    }
}
