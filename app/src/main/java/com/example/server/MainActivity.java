package com.example.server;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.server.measurements.Measurements;
import com.example.server.my.Server01;


public class MainActivity extends AppCompatActivity {

    private static final String TAG= "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissions();

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Measurements measurements = new Measurements(telephonyManager, locationManager, this);

        TextView tv = findViewById(R.id.textView);

        findViewById(R.id.btnMeasure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("Telephone Data = " + measurements.getPhoneInfo());
            }
        });

        findViewById(R.id.btnLocation).setOnClickListener(v -> tv.setText("Latitude is = " + measurements.getLatitude()));

        findViewById(R.id.btnStartServer).setOnClickListener(v -> {
                Intent intent = new Intent(this, ServerActivity.class);
                startActivity(intent);
            });
    }

    private void checkPermissions(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, permissions, 1001);
        }
    }

}