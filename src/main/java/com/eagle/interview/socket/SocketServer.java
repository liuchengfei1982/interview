package com.eagle.interview.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(8081);
		System.out.println("Scoket 服务启动成功。。。。。。。。。。。。。");
		Socket client = server.accept();
		System.out.println("来访问客户端信息：" + "    客户端ip：" + client.getInetAddress() + "客户端端口："
				+ client.getInetAddress().getLocalHost() + "已连接服务器");
		BufferedReader bReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		String msg = bReader.readLine();
		System.out.println("客户端发来的消息：" + msg);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		bw.write("服务端返回的信息: " + msg + "\n");
		bw.flush();
	}
}