package com.example.server.my;

import android.content.Context;
import android.media.AudioManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.server.functionalities.Audio;
import com.example.server.net.Server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

/**
 * A very simple-to-use Server class for Java network applications<br>
 * inspired by Leonard Bienbeck at https://www.youtube.com/watch?v=NHCCJtS_GGY&list=PLxXRtbg-a3UqwfDzYTvfe3I7qAb7o3R2U&index=18
 *
 * @author Jürgen Dieterle
 * @version 1.0.0
 */

public class Server01 {

    private int port;
    private Thread listeningThread;
    private ServerSocket serverSocket;
    private AudioManager audioManager;
    private TelephonyManager telephonyManager;
    private ArrayList<RemoteClient> clients;

    /**
     * @param port The port to listen on
     */
    public Server01(int port, AudioManager audioManager, TelephonyManager telephonyManager){
        this.port = port;
        this.audioManager = audioManager;
        this.telephonyManager = telephonyManager;
        this.clients = new ArrayList<RemoteClient>();
    }

    /**
     * Starts the listening thread if not already started
     */
    public void startListening(){
        if (listeningThread == null) {
            listeningThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!Thread.interrupted()) {
                        try {
                            serverSocket = new ServerSocket(port);
                            Socket remoteClientSocket = serverSocket.accept();
                            clients.add(new RemoteClient(UUID.randomUUID().toString(), remoteClientSocket));

                            // Read the client's message
                            //ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(remoteClientSocket.getInputStream()));
                            //Object raw = ois.readObject();
                            int vol = 0;
                            Scanner s = new Scanner(new BufferedReader(new InputStreamReader(remoteClientSocket.getInputStream())));
                            if (s.hasNextLine()) {
                                vol = Integer.parseInt(s.nextLine());
                                System.out.println("[Server] Message from client: " + vol);

                            }


                            int maxLoud = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, vol, 0);

                            PrintWriter pw = new PrintWriter(new OutputStreamWriter(remoteClientSocket.getOutputStream()));
                            pw.println("set to" +vol);
                            pw.flush();


                            remoteClientSocket.close();
                            serverSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
            listeningThread.start();

        }
    }

    public int getClientCount() {
        return clients != null ? clients.size() : 0;
    }

}

