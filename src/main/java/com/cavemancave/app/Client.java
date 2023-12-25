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
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

// [read different messagetype](https://stackoverflow.com/questions/5680259/using-sockets-to-send-and-receive-data)
//https://www.codeproject.com/Tips/991180/Java-Sockets-and-Serialization
public class Client {
	Socket sock;
	InputStream input;
	OutputStream output;
	DataOutputStream dataOut;
	DataInputStream dataIn;

	public static void main(String[] args) throws IOException {
		Socket sock = new Socket("0.0.0.0", 6666); // 连接指定服务器和端口
		try (InputStream input = sock.getInputStream()) {
			try (OutputStream output = sock.getOutputStream()) {
				handle(input, output);
			}
		}
		sock.close();
		System.out.println("[client] client disconnected.");
	}

	public Client(String server, int port) throws IOException {
		Socket sock = new Socket(server, port); // 连接指定服务器和端口
		this.sock = sock;
		this.input = sock.getInputStream();
		this.output = sock.getOutputStream();
		this.dataIn = new DataInputStream(this.input);
		this.dataOut = new DataOutputStream(this.output);
		System.out.println(sock.isClosed());
	}

	public String SendLogin(String name, String passwd) throws IOException {
		System.out.println("send login");
		System.out.println(this.sock.isClosed());
		DataOutputStream dOut = new DataOutputStream(sock.getOutputStream());
		dOut.write(1);
		this.output.write(1);
		this.dataOut.writeByte(1);
		this.dataOut.writeUTF(name + ' ' + passwd);
		this.dataOut.flush();
		String returnMsg = this.dataIn.readUTF();
		return returnMsg;
	}

	public void SendPosition(int index, int x, int y) throws IOException {
		this.dataOut.writeByte(2);
		this.dataOut.writeByte(index);
		this.dataOut.writeByte(x);
		this.dataOut.writeByte(y);
	}

	private static void handle(InputStream input, OutputStream output) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
		BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
		Scanner scanner = new Scanner(System.in);
		System.out.println("[server] " + reader.readLine());
		for (;;) {
			System.out.print(">>> "); // 打印提示
			String s = scanner.nextLine(); // 读取一行输入
			writer.write(s);
			writer.newLine();
			writer.flush();
			String resp = reader.readLine();
			System.out.println("<<< " + resp);
			if (resp.equals("bye")) {
				break;
			}
		}
	}
}
