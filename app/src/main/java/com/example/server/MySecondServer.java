package com.example.server;

import androidx.appcompat.app.AppCompatActivity;

import com.example.server.functionalities.Audio;
import com.example.server.net.Datapackage;
import com.example.server.net.Executable;
import com.example.server.net.Server;

import java.net.Socket;

public class MySecondServer extends Server {

    public MySecondServer() {
        super(7000, true, true, false, false);
    }

    @Override
    public void preStart() {
       this.registerMethod("VOL_RAISE", new Executable() {
           @Override
           public void run(Datapackage pack, Socket socket) {
            sendReply(socket, "OK"); //send reply
               Audio audio = new Audio();
               audio.setVolume(5);
           }
       });
    }
}
