package com.eagle.interview.drawimg;


import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

/**
 * @author tangyouhua
 *         <p/>
 *         描述：二维码生成工具类 ，使用该工具类需要引入zxing.jar
 */
public class QRCodeUtil {

	/**
	 * 删除二维码白边
	 *
	 * @param bitMatrix
	 * @return
	 */
	public static BitMatrix deleteWhite(BitMatrix bitMatrix) {
		int[] rec = bitMatrix.getEnclosingRectangle();
		int resWidth = rec[2] + 1;
		int resHeight = rec[3] + 1;

		BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
		resMatrix.clear();
		for (int i = 0; i < resWidth; i++) {
			for (int j = 0; j < resHeight; j++) {
				if (bitMatrix.get(i + rec[0], j + rec[1])) {
					resMatrix.set(i, j);
				}
			}
		}
		return resMatrix;
	}


	/**
	 * 生成二维码
	 *
	 * @param content
	 *            二维码内容
	 * @param hasFrame
	 *            是否删除白边
	 * @throws Exception
	 */
	public static BufferedImage createQrCode(String content, boolean hasFrame,
											 int qrCodeSize) throws Exception {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 1);

		// 二维码的默认尺寸为 172像素
		BitMatrix bitMatrix =
				new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hints);
		// 删除白边
		if (hasFrame) {
			bitMatrix = QRCodeUtil.deleteWhite(bitMatrix);
		}
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
			}
		}

		return image;
	}

	/**
	 * 条形码编码
	 *
	 * @param contents
	 * @param width
	 * @param height
	 */
	public static BufferedImage createBarCode(String contents, int width, int height) {
		BarcodeFormat barcodeFormat = BarcodeFormat.CODE_128;

		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, barcodeFormat, width, height, null);

			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
				}
			}
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 条形码解码
	 *
	 * @param imgPath
	 * @return String
	 */
	public static String decode(String imgPath) {
		BufferedImage image = null;
		Result result = null;
		try {
			image = ImageIO.read(new File(imgPath));
			if (image == null) {
				System.out.println("the decode image may be not exit.");
			}
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			result = new MultiFormatReader().decode(bitmap, null);
			return result.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
