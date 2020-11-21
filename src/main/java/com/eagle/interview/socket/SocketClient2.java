package com.eagle.interview.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class SocketClient2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("开始");
		String str = "123";
		String startRow = "SRGQTPUSH Request V1.0\r\n";
		VPTTinfo vPTTinfo = new VPTTinfo();
		vPTTinfo.setDevice_name("G4444");
		vPTTinfo.setDevice_serialNum("147059816");
		vPTTinfo.setDevice_status(0);

		// Socket clientSocket; // 创建客户端套接字
		try {
			// 指定连接服务端的IP  端口
			Socket socket = new Socket("10.79.10.77", 6506);
			InputStream iStream = socket.getInputStream();
			OutputStream oStream = socket.getOutputStream();
			// FileInputStream fileInputStream = new
			// FileInputStream("C:\\Users\\kouzi\\Desktop\\test.txt");
			// InputStreamReader inputStreamReader = new
			// InputStreamReader(fileInputStream);
			// byte[] buffer = new byte[1024];
			// int len = fileInputStream.read(buffer);
			String hexstring = "4e4848594d41524b8fedfae12dc4d04d85a55ceb8cb035a70000000100000001310000000000000000000000000000000000000000000000000000000000008b7b22496e707574506172616d65746572223a7b224163636f756e74223a2261646d696e227d2c224f706572617465223a224b656570222c2274797065223a22486561727462656174227d0a";
			byte[] destByte = new byte[hexstring.length() / 2];
			int j = 0;
			for (int i = 0; i < destByte.length; i++) {
				byte high = (byte) (Character.digit(hexstring.charAt(j), 16) & 0xff);
				byte low = (byte) (Character.digit(hexstring.charAt(j + 1), 16) & 0xff);
				destByte[i] = (byte) (high << 4 | low);
				j += 2;
			}
			oStream.write(destByte);
//			oStream.write("{12345\r\n".getBytes());
			oStream.flush();
//			BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(oStream));
//			bWriter.write("{12345\r\n");
//			bWriter.flush();
			BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream,Charset.forName("UTF-8")));
			String line = null;
			String ackMsg = "";
			while((line = bReader.readLine()) != null){
				ackMsg  = ackMsg + line;
				System.out.println(line);
			}
//			System.out.println(ackMsg);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}