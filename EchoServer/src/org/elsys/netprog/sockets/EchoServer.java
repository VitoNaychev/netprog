package org.elsys.netprog.sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class EchoServer {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		final Object  lock = new Object();

		ArrayList<String> msgArray = new ArrayList<String>();
		ArrayList<PrintWriter> outArray = new ArrayList<PrintWriter>();

		try {
			serverSocket = new ServerSocket(10001);
			ServerSendThread sendThread = new ServerSendThread(outArray, lock, msgArray);
			sendThread.start();

		    while(true) {
                Socket clientSocket = serverSocket.accept();
				outArray.add(new PrintWriter(clientSocket.getOutputStream(), true));
				new ServerRecvThread(clientSocket, lock, msgArray).start();
                System.out.println("client connected from " + clientSocket.getInetAddress());
		    }

		} catch (Throwable t) {
			System.out.println(t.getMessage());
		} finally {
			if (serverSocket != null && !serverSocket.isClosed()) {
				//serverSocket.close();
			}
			
			System.out.println("Server closed");
		}
	}

}
