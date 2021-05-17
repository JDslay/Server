package com.example.server;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.server.measurements.Measurements;

/**
 * Get location dependent measurements, store locally, give also the possibility to
 * access data of your phone by a client or give remote control
 *
 * @author Jürgen Dieterle
 * @version 1.0.0
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissions();
        Measurements measurements = initMeasurements();

        TextView infoBox = findViewById(R.id.results_text);
        View btnMeasure = findViewById(R.id.btnMeasure);
        View btnLocateMe = findViewById(R.id.btnLocation);
        View btnStartServer = findViewById(R.id.btnStartServer);

        btnMeasure.setOnClickListener(v -> {
            infoBox.setText("Telephone: " + measurements.getPhoneInfo());
            findViewById(R.id.linearLayout_results).setVisibility(View.VISIBLE);
        });
        btnLocateMe.setOnClickListener(v -> infoBox.setText("Latitude is = " + measurements.getLatitude()));
        btnStartServer.setOnClickListener(v -> {
            Intent intent = new Intent(this, ServerActivity.class);
            startActivity(intent);
        });
    }

    private void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, permissions, 1001);
        }
    }

    private Measurements initMeasurements() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return new Measurements(telephonyManager, locationManager, this);
    }

}