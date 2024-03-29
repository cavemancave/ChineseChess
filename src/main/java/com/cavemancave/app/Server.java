package com.cavemancave.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(6666); // 监听指定端口
		System.out.println("[server] server is running...");
		for (;;) {
			Socket sock = ss.accept();
			System.out.println("[server] connected from " + sock.getRemoteSocketAddress());
			Thread t = new Handler(sock);
			t.start();
		}
	}
}

class Handler extends Thread {
	Socket sock;

	public Handler(Socket sock) {
		this.sock = sock;
	}

	@Override
	public void run() {
		try (InputStream input = this.sock.getInputStream()) {
			try (OutputStream output = this.sock.getOutputStream()) {
				handle(input, output);
			}
		} catch (Exception e) {
			try {
				this.sock.close();
			} catch (IOException ioe) {
			}
			System.out.println("[server] client disconnected.");
		}
	}

	private void handle(InputStream input, OutputStream output) throws IOException {
		DataInputStream dataIn = new DataInputStream(input);
		for (;;) {
			byte messageType = dataIn.readByte();
			System.out.println("got a message");
			switch (messageType) {
			case 1:
				handleLogin(input, output);
				break;
			case 2:
				handlePosition(input, output);
				break;
			default:
				System.out.println("[server] Wrong message");
			}
		}

		/*
		 * var writer = new BufferedWriter(new OutputStreamWriter(output,
		 * StandardCharsets.UTF_8)); var reader = new BufferedReader(new
		 * InputStreamReader(input, StandardCharsets.UTF_8)); writer.write("hello\n");
		 * writer.flush(); for (;;) { String s = reader.readLine(); if (s.equals("bye"))
		 * { writer.write("bye\n"); writer.flush(); break; } writer.write("ok: " + s +
		 * "\n"); writer.flush(); }
		 */
	}

	private void handlePosition(InputStream input, OutputStream output) {
		// TODO Auto-generated method stub

	}

	private void handleLogin(InputStream input, OutputStream output) throws IOException {
		// TODO Auto-generated method stub
		DataOutputStream dataOut = new DataOutputStream(output);
		Scanner s = new Scanner(input);
		String name = s.next();
		String passwd = s.next();
		if (name.equals(passwd)) {
			dataOut.writeUTF("OK");
		} else {
			dataOut.writeUTF("Wrong password");
		}
		dataOut.flush();
	}
}
