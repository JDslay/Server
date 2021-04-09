package com.example.server;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        MySecondServer server = null;

        findViewById(R.id.btnStartServer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MySecondServer server = new MySecondServer();
                //MyFirstServer server = new MyFirstServer();
                //server.startListening();
                Log.d("SERVER2", "works");
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Anzahl aktiver Clients" + server.getClientCount());
            }
        });

        findViewById(R.id.btnNbrClients).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = findViewById(R.id.textView);
                if (server == null)
                {
                    MySecondServer server = new MySecondServer();
                    text.setText("Anzahl aktiver Clients = " + server.getClientCount());
                }
                else {
                    text.setText("Anzahl aktiver Clients = " + server.getClientCount());
                }

                                                                }
                                                            }
        );
    }
}