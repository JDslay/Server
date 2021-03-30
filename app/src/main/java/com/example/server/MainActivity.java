package com.example.server;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        findViewById(R.id.btnStartServer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("yeah");
                MainActivity server = new MainActivity();
                server.startListening();
            }
        });
    }
    public MainActivity(){
    }

    private void startListening() {
        System.out.println("[Server] starten...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        ServerSocket serverSocket = new ServerSocket(7000);
                        System.out.println("[Server] Warten auf Verbindung....");
                        Socket remoteClientSocket = serverSocket.accept();
                        System.out.println("[Server] Client verbunden: " + remoteClientSocket.getRemoteSocketAddress());

                        Scanner s = new Scanner(new BufferedReader(new InputStreamReader(remoteClientSocket.getInputStream())));
                        if (s.hasNextLine()) {
                            System.out.println("[Server] Message from client: " + s.nextLine());
                        }

                        PrintWriter pw = new PrintWriter(new OutputStreamWriter(remoteClientSocket.getOutputStream()));
                        pw.println("Hi Client");
                        pw.flush();
                        // Verbindung schließen
                        s.close();
                        remoteClientSocket.close();
                        serverSocket.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}