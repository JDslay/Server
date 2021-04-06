package com.example.server;

import com.example.server.net.Server;

public class MySecondServer extends Server {

    public MySecondServer() {
        super(7500, true, true, false, false);
    }

    @Override
    public void preStart() {
       // this.registerMethod("TIME_REQUEST", new);
    }
}
