package com.example.server;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.server.my.Server01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //elephonyManager mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //@SuppressLint("MissingPermission") String a = mTelephonyManager.getDeviceSoftwareVersion();
                //.listen(mPhoneStatelistener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        Server01 server = new Server01(7000, audioManager);
        findViewById(R.id.btnStartServer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                server.startListening();
                //MyFirstServer server = new MyFirstServer();
                //server.startListening();
                /*Log.d("SERVER2", "works");
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Anzahl aktiver Clients" + server.getClientCount());
                 */
            }
        });


        findViewById(R.id.btnNbrClients).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.textView);
                text.setText("Anzahl aktiver Clients = " + server.getClientCount());

                                                                }
                                                            }
        );
    }
}