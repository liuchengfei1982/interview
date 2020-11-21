package com.eagle.interview.IKM;

import java.io.*;

/**
 * Reader & Writer
 * InputStream & OutputStream
 */
public class Q2 {
	public static void main(String[] args) throws Exception {
		FileReader fileReader = new FileReader("filePath");
		BufferedReader br1 = new BufferedReader(fileReader);
//		BufferedReader br2 = new BufferedReader(new File("filePath")); //wrong
//		InputStreamReader isr = new InputStreamReader(""); // wrong
	}
}
