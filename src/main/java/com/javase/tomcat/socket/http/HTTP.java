package com.javase.tomcat.socket.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class HTTP {
	public static void main(String[] args) {
		int port = 8080;
		try {
			Socket socket = new Socket("127.0.0.1", port);
			OutputStream os = socket.getOutputStream();
			InputStream is = socket.getInputStream();
			boolean autoFlush = true;
			PrintWriter out = new PrintWriter(os, autoFlush);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			// send an HTTP request to web server
			out.println("GET /index.jsp HTTP/1.1");
			out.println("Host: localhost:8080");
			out.println("Connection: Close");
			out.println();
			
			// read the response
			boolean loop = true;
			StringBuffer sb = new StringBuffer(8096);
			while(loop) {
				if(reader.ready()) {
					int i=0;
					while(i!=-1) {
						i=reader.read();
						sb.append((char)i);
					}
					loop = false;
				}
				try {
					Thread.currentThread().sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// display the response to the out console
				System.out.println(sb.toString());
				socket.close();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
