package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class ServerRecvThread extends Thread {
    Socket socket;
    BufferedReader in;
    final Object lock;
    ArrayList<String> msgArray;

    public ServerRecvThread(Socket socket, Object lock, ArrayList<String> msgArray) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.lock = lock;
        this.msgArray = msgArray;
    }

    public void run(){
        try {
            while (!socket.isClosed()) {
                String clientMsg = in.readLine();
                synchronized (lock) {
                    if (clientMsg != null) {
                        msgArray.add(clientMsg);
                        System.out.println(clientMsg);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
