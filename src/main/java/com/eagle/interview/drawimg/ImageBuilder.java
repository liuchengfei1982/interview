package com.eagle.interview.drawimg;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class ImageBuilder {

	private static int screenWidth = 800;
	private static int screenHeight = 480;
	private static int logoWidth = 350;
	private static int logoHeight = 70;
	private static int leftPadding = 30;
	private static int barLeftPadding = 10;
	private static int topPadding = 25;
	private static int barWidth =700;
	private static int barHeight =100;
	private static int qrWidth =170;
	private static int qrHeight =170;


	private static String logoPath;
	private static String prefix;

	static {

		ImageBuilder.prefix = "D:\\project-idea\\codeprinter\\src\\main\\resources\\images\\";
		ImageBuilder.logoPath=prefix +"csairLogo1.png";
	}

	/**
	 *
	 * @return 最新图片生成路径
	 */
	public static String drawImg(FlightInfo info) {
		//得到图片缓冲区
		FileInputStream logoFileInputStream = null;
		try {
			BufferedImage bi = new BufferedImage(ImageBuilder.screenWidth, ImageBuilder.screenHeight, BufferedImage.TYPE_INT_RGB);

			logoFileInputStream = new FileInputStream(ImageBuilder.logoPath);
			BufferedImage logoImg = ImageIO.read(logoFileInputStream);
			//得到它的绘制环境(这张图片的笔)
			Graphics2D g2 = (Graphics2D) bi.getGraphics();
			// 填充区域
			g2.fillRect(0, 0, screenWidth, screenHeight);
			// =======================logo start======================
			// 设置颜色
			g2.setColor(Color.WHITE);
			g2.drawImage(logoImg, leftPadding+150, topPadding, logoWidth, logoHeight,null);
			g2.drawRect(0, 0, screenWidth - 1, screenHeight - 1);
			// =======================logo end======================

			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke(4.0f));
			g2.drawLine(leftPadding,topPadding+80,leftPadding+680,topPadding+80);

			// =======================条码 start======================
			// 条形码图片
			g2.setColor(Color.WHITE);
			BufferedImage barImg = QRCodeUtil.createBarCode(info.getPrefix() + info.getContent(), 150, 50);
			g2.drawImage(barImg, barLeftPadding, topPadding+90, barWidth, barHeight,null);
			// 右侧文字
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("黑体", Font.PLAIN, 40));
			g2.drawString(info.getSeqNoStr().toString(), leftPadding + 610, topPadding + 160);
			g2.setStroke(new BasicStroke(2.0f));
			g2.drawLine(leftPadding, topPadding + 200, leftPadding + 680, topPadding + 200);
			// 左侧文字，覆盖部分条码
			// 设置字体:字体、字号、大小
			g2.setFont(new Font("黑体", Font.PLAIN, 50));
			g2.drawString(info.getLevel(), leftPadding + 20, topPadding + 160);
			// =======================条码 end======================

			// =======================二维码 start=====================
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("黑体", Font.PLAIN, 60));
			g2.drawString(info.getPrefix(), leftPadding + 30, topPadding + 250);
			// 二维码图片
			g2.setColor(Color.WHITE);
			BufferedImage qrCode = QRCodeUtil.createQrCode(info.getPrefix() + info.getContent(), true, 172);
			g2.drawImage(qrCode, leftPadding, topPadding + 260, qrWidth, qrHeight, null);

			g2.setColor(Color.BLACK);
			g2.setFont(new Font("黑体", Font.PLAIN, 60));
			g2.drawString(info.getContent(), leftPadding + 210, topPadding + 260);
			g2.drawLine(leftPadding + 200, topPadding + 280, leftPadding + 520, topPadding + 280); // 横线
			g2.drawLine(leftPadding + 200, topPadding + 380, leftPadding + 520, topPadding + 380); // 横线
			g2.drawLine(leftPadding + 320, topPadding + 280, leftPadding + 320, topPadding + 380); // 竖线
			g2.drawLine(leftPadding + 520, topPadding + 200, leftPadding + 520, topPadding + 380); // 竖线

			g2.setFont(new Font("黑体", Font.PLAIN, 20));
			g2.drawString("件数", leftPadding + 200, topPadding + 310);
			g2.setFont(new Font("黑体", Font.BOLD, 40));
			if (info.getPackageNum() != null) {
				g2.drawString(String.valueOf(info.getPackageNum()), leftPadding + 210, topPadding + 360);
			}

			g2.setFont(new Font("黑体", Font.PLAIN, 20));
			g2.drawString("重量", leftPadding + 330, topPadding + 310);
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("黑体", Font.BOLD, 40));
			if (info.getWeight() != null) {
				g2.drawString(info.getWeight(), leftPadding + 340, topPadding + 360);
			}

			g2.setColor(Color.BLACK);
			g2.setFont(new Font("黑体", Font.PLAIN, 20));
			g2.drawString("始发站", leftPadding + 210, topPadding + 420);
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("黑体", Font.BOLD, 40));
			g2.drawString(info.getDep(), leftPadding + 310, topPadding + 420);

			g2.setColor(Color.BLACK);
			g2.setFont(new Font("黑体", Font.PLAIN, 20));
			g2.drawString("目的站", leftPadding + 530, topPadding + 230);
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("黑体", Font.PLAIN, 70));
			if(info.getDestination().length()>2){
				String sub1 = info.getDestination().substring(0, 2);
				String sub2 = info.getDestination().substring(sub1.length(),info.getDestination().length());
				g2.drawString(sub1, leftPadding + 540, topPadding + 320);
				g2.drawString(sub2, leftPadding + 540, topPadding+390);
			}else {
				g2.drawString(info.getDestination(), leftPadding + 560, topPadding + 320);
			}

			//=======================二维码 end======================


			// 图片上传后的路径
			String savePath = "F:/new.bmp";
			ImageIO.write(bi, "BMP", new FileOutputStream(savePath));

			//测试条形码解码
//			String barPath = "F:/barCode.jpg";
//			ImageIO.write(barImg, "JPEG", new FileOutputStream(barPath));
//			String decode = QRCodeUtil.decode(barPath);
//			log.info("the bar code result: {}", decode);

			return savePath;
		}catch (Exception e){
			log.error("生成图片错误",e);
			throw new BusinessException("生成图片错误",e);
		}finally {
			if(logoFileInputStream != null){
				try {
					logoFileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		ImageBuilder.FlightInfo flightInfo = new ImageBuilder.FlightInfo();
		flightInfo.setDep("广州");
		flightInfo.setDestination("上海浦东");
		flightInfo.setLevel("A");
		flightInfo.setPrefix("784");
		flightInfo.setSeqNoStr("0001");
		flightInfo.setPackageNum(new Short("102"));
		flightInfo.setWeight("2000");
		flightInfo.setContent("27900331");

		String path =  ImageBuilder.drawImg(flightInfo);
		log.info("图片生成成功: {}",path);
	}


	@Data
	static class FlightInfo{
		private String dep; // 始发
		private String destination; // 目的站
		private String level; // 保障等级
		private String content; // 标签
		private String prefix; // 运单前缀
		private String seqNoStr; // 4位流水号 根据打印页码设置生成
		private Short packageNum; // 件数
		private String weight; // 重量
		private String bookingWeight; // 订舱重量
		private Short bookingPackageNum; // 订舱件数
		private String specialFlag;//特殊标记
	}
}
