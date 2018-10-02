package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SendThread extends Thread {
    PrintWriter out;
    BufferedReader stdIn;
    Socket socket;

    public SendThread(Socket socket) throws IOException {
        this.socket = socket;
        this.stdIn = new BufferedReader(new InputStreamReader(System.in));

        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void run(){
        try {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                if(userInput == "exit"){
                    socket.close();
                }
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }
}
