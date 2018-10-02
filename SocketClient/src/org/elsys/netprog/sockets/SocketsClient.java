package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class SocketsClient {

	public static void main(String[] args) throws IOException {
		Socket echoSocket = null;
		try {
			    echoSocket = new Socket("localhost", 10001);

				new SendThread(echoSocket).start();
				new RecvThread(echoSocket).start();
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		} finally {
			if (echoSocket != null && !echoSocket.isClosed()) {
				//echoSocket.close();
			}
		}
	}

}
