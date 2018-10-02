package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RecvThread extends Thread {
    Socket socket;
    BufferedReader in;

    public RecvThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run(){
        try {
            while(!socket.isClosed()){
                String serverMsg = in.readLine();
                if(serverMsg != null) {
                    System.out.println(serverMsg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
