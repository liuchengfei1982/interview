package com.eagle.interview.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("开始");
		String str = "123";
		Socket clientSocket; // 创建客户端套接字
		try {
			// 指定连接服务端的IP  端口
			Socket socket = new Socket("10.79.10.77", 6506);
			InputStream iStream = socket.getInputStream();
			OutputStream oStream = socket.getOutputStream();
			oStream.write("{1234".getBytes("UTF-8"));
			oStream.flush();
			BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(oStream));
			bWriter.write("{12345/n");
			bWriter.flush();
			BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream));
			String msg = bReader.readLine();
			System.out.println(msg);


		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}