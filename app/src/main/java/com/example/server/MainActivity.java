package com.example.server;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.server.db.Measurement;
import com.example.server.db.MeasurementDAO;
import com.example.server.db.MeasurementDatabase;
import com.example.server.measurements.Measurements;

import java.util.List;

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
        View btnSave = findViewById(R.id.btnSave);

        btnMeasure.setOnClickListener(v -> {
            infoBox.setText("Telephone: " + measurements.getPhoneInfo());
            findViewById(R.id.linearLayout_results).setVisibility(View.VISIBLE);
        });
        btnLocateMe.setOnClickListener(v -> infoBox.setText("Latitude is = " + measurements.getLatitude()));
        btnStartServer.setOnClickListener(v -> {
            Intent intent = new Intent(this, ServerActivity.class);
            startActivity(intent);
        });
        btnSave.setOnClickListener(v -> {
            Thread thread = new Thread(){
                public void run(){
                    MeasurementDatabase db = Room.databaseBuilder(getApplicationContext(),
                            MeasurementDatabase.class, "database-name").build();
                    MeasurementDAO measurementDao = db.measurementDAO();
                    measurementDao.insertMeasurement(new Measurement(2,4,5));
                    measurementDao.insertMeasurement(new Measurement(3,5,6));
                    List<Measurement> measurementss = measurementDao.getAllMeasurements();
                    double a = measurementss.get(1).fieldStrength;
                    LiveData<List<Measurement>> data = measurementDao.getAllMeasurementsSortedByFieldStrength();
                    List<Measurement> list = data.getValue();
                    Log.d("DBASE","[fs is"+a);
                }
            };

            thread.start();




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