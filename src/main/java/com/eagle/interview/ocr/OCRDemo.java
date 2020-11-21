package com.eagle.interview.ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OCRDemo {
	public static void main(String args[]) throws Exception {
		ITesseract instance = new Tesseract();
		instance.setDatapath("tessdata"); //相对目录，这个时候tessdata目录和src目录平级

//        instance.setDatapath("E:\\myProgram\\java\\ocrdemo\\tessdata");//支持绝对目录
//		instance.setLanguage("eng");//选择字库文件（只需要文件名，不需要后缀名）

		try {
			File imageFile = new File("E:\\study\\ocr\\xiaopiao7.png");
//			instance.setLanguage("chi_sim");
			instance.setLanguage("eng");
			instance.setOcrEngineMode(0);
			String result = instance.doOCR(imageFile);//开始识别
			System.out.println(result);
//			String[] datas=result.split("\\n");
//			String num = datas[0].split("\\.")[0];
//			String money = datas[datas.length-1].split("\\.")[0];
//			System.out.println("=========================");
//
//			String regEx="[^0-9]";
//			Pattern p = Pattern.compile(regEx);
//			Matcher n = p.matcher(num);
//			Matcher m = p.matcher(money);
//			System.out.println( n.replaceAll("").trim());
//			System.out.println( m.replaceAll("").trim());

//			for (int i = 0; i < datas.length; i++) {
//				System.out.println(datas[i]);
//			}




		} catch (Exception e) {
			System.out.println(e.toString());//打印图片内容
		}
	}
}
