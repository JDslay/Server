package com.example.server;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.server.my.Server01;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


public class MainActivity extends AppCompatActivity {

    private static final String TAG= "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] permissions = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        ActivityCompat.requestPermissions(this, permissions, 1001);


        //
        //double h = lo.getAltitude();


        //@SuppressLint("MissingPermission") String a = mTelephonyManager.getDeviceSoftwareVersion();
        //.listen(mPhoneStatelistener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        Log.d(TAG, "onCreate: "+ loc.toString());

        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
            locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null){
                        Log.d(TAG, "onSuccess: " + location.toString());
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











        Server01 server = new Server01(7000, audioManager, telephonyManager);
        TextView tv = findViewById(R.id.textView);

        findViewById(R.id.btnMeasure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignalStrength info = telephonyManager.getSignalStrength();
                tv.setText("Telephone Data = " + info.toString());
            }
        });

        findViewById(R.id.btnLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               tv.setText("Latitude is = " + loc.getLatitude());
            }
        });

        findViewById(R.id.btnStartServer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                server.startListening();
            }
        });

        findViewById(R.id.btnNbrClients).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("Anzahl aktiver Clients = " + server.getClientCount());

                                                                }
                                                            }
        );
    }
}