package com.eagle.interview.ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.opencv.core.Core.NATIVE_LIBRARY_NAME;

public class OCRDemo2 {
	static {

		//注意程序运行的时候需要在VM option添加该行 指明opencv的dll文件所在路径
		//-Djava.library.path=$PROJECT_DIR$\opencv\x64
		System.loadLibrary(NATIVE_LIBRARY_NAME);   //载入opencv all库
	}

	public static void main(String[] args) throws InterruptedException{
		/**
		 * 1. 读取原始图像转换为OpenCV的Mat数据格式
		 */
		Mat srcMat = Imgcodecs.imread("E://study//ocr//opencvimg//xiaopiao7.png");  //原始图像

		/**
		 * 2. 强原始图像转化为灰度图像
		 */
		Mat grayMat = new Mat(); //灰度图像
		Imgproc.cvtColor(srcMat, grayMat, Imgproc.COLOR_RGB2GRAY);
		BufferedImage grayImage =  toBufferedImage(grayMat);
		saveJpgImage(grayImage,"E://study//ocr//opencvimg//grayImage.jpg");
		System.out.println("保存灰度图像！");

		/**
		 * 3、对灰度图像进行二值化处理
		 */
		Mat binaryMat = new Mat(grayMat.height(),grayMat.width(), CvType.CV_8UC1);
		Imgproc.threshold(grayMat, binaryMat, 100, 400, Imgproc.THRESH_BINARY);
//		Imgproc.threshold(grayMat, binaryMat, 20, 255, Imgproc.THRESH_BINARY);
		BufferedImage binaryImage =  toBufferedImage(binaryMat);
		saveJpgImage(binaryImage,"E://study//ocr//opencvimg//binaryImage.jpg");
		System.out.println("保存二值化图像！");

		/**
		 * 4、识别
		 */
		ocr(binaryImage);
	}

	private static void ocr(BufferedImage img){
		ITesseract instance = new Tesseract();
		instance.setDatapath("tessdata"); //相对目录，这个时候tessdata目录和src目录平级

//        instance.setDatapath("E:\\myProgram\\java\\ocrdemo\\tessdata");//支持绝对目录
		instance.setLanguage("eng");//选择字库文件（只需要文件名，不需要后缀名）

		try {
//			File imageFile = new File("E:\\study\\ocr\\opencvimg\\binaryImage.jpg");
//			instance.setLanguage("chi_sim");
			String result = instance.doOCR(img);//开始识别
			System.out.println(result);
//			String[] datas=result.split("\\n");
//			String num = datas[0].split("\\.")[0];
//			String money = datas[datas.length-1].split("\\.")[0];
//			System.out.println("=========================");

//			String regEx="[^0-9]";
//			Pattern p = Pattern.compile(regEx);
//			Matcher n = p.matcher(num);
//			Matcher m = p.matcher(money);
//			System.out.println( n.replaceAll("").trim());
//			System.out.println( m.replaceAll("").trim());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * 将Mat图像格式转化为 BufferedImage
	 * @param matrix  mat数据图像
	 * @return BufferedImage
	 */
	private static BufferedImage toBufferedImage(Mat matrix) {
		int type = BufferedImage.TYPE_BYTE_GRAY;
		if (matrix.channels() > 1) {
			type = BufferedImage.TYPE_3BYTE_BGR;
		}
		int bufferSize = matrix.channels() * matrix.cols() * matrix.rows();
		byte[] buffer = new byte[bufferSize];
		matrix.get(0, 0, buffer); // 获取所有的像素点
		BufferedImage image = new BufferedImage(matrix.cols(), matrix.rows(), type);
		final byte[] targetPixels = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
		System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
		return image;
	}


	/**
	 * 将BufferedImage内存图像保存为图像文件
	 * @param image BufferedImage
	 * @param filePath  文件名
	 */
	private static void saveJpgImage(BufferedImage image, String filePath) {

		try {
			ImageIO.write(image, "jpg", new File(filePath));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
