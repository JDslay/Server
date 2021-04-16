package com.example.server.my;

import java.net.Socket;

class RemoteClient {

    private String id;
    private Socket socket;

    protected RemoteClient(String id, Socket socket) {
        this.id = id;
        this.socket = socket;
    }

    protected String getId() {
        return id;
    }

    protected Socket getSocket() {
        return socket;
    }

    @Override
    public String toString() {
        return "[RemoteClient: " + id + "  @ " + socket.getRemoteSocketAddress() + "]";
    }
}

