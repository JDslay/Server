package com.example.server.measurements;

import android.location.LocationManager;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;

public class Measurements {

    private TelephonyManager telephonyManager;
    private LocationManager locationManager;

    public Measurements(TelephonyManager telephonyManager, LocationManager locationManager) {
        this.telephonyManager = telephonyManager;
        this.locationManager = locationManager;
    }

    public String getPhoneInfo(){
        SignalStrength info = telephonyManager.getSignalStrength();
        return info.toString();
    }

    public double getLatitude(){
        return 0;
    }
}
