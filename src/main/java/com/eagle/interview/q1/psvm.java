package com.eagle.interview.q1;

import java.io.*;

public class psvm {
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream(new File("").getAbsolutePath());
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader inputStream = new BufferedReader(isr);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}



	}
}
