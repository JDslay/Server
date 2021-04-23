package com.example.server;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.server.my.Server01;

public class ServerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        Server01 server = new Server01(7000, audioManager);

        TextView tv = findViewById(R.id.tvNbrClients);
        findViewById(R.id.btnStartListening).setOnClickListener(v -> server.startListening());
        findViewById(R.id.btnNbrClients).setOnClickListener(v -> tv.setText("Anzahl aktiver Clients = " + server.getClientCount())
        );
    }
}
