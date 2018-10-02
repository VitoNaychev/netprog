package org.elsys.netprog.sockets;

import javax.naming.event.ObjectChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSendThread extends Thread {
    ArrayList<PrintWriter> outArray;
    ArrayList<String> msgArray;
    final Object lock;

    public ServerSendThread(ArrayList<PrintWriter> outArray, Object lock, ArrayList<String> msgArray){
        this.outArray = outArray;
        this.lock = lock;
        this.msgArray = msgArray;

    }

    public void run(){
        try{
            int prevSize = msgArray.size();

            while (true) {
                synchronized(lock) {
                    if(prevSize != msgArray.size()) {

                        for (int i = 0; i < outArray.size(); ++i) {
                            outArray.get(i).println(msgArray.get(msgArray.size() - 1));
                        }
                        prevSize = msgArray.size();
                    }
                }
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }
}
