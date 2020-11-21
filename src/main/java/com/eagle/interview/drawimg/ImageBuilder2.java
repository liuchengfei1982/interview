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
public class ImageBuilder2 {

	private static int screenWidth;
	private static int screenHeight;
	private static int logoWidth = 600;
	private static int logoHeight = 80;
	private static int leftPadding = 65;
	private static int barLeftPadding = 0;
	private static int topPadding = 110;
	private static int barWidth =750;
	private static int barHeight =100;


	private static String logoPath;
	private static String prefix;

	static {
		ImageBuilder2.screenWidth = 800;
		ImageBuilder2.screenHeight = 800;


		ImageBuilder2.prefix = "D:\\project-idea\\codeprinter\\src\\main\\resources\\images\\";
		ImageBuilder2.logoPath=prefix +"csairLogo1.png";
	}

	/**
	 *
	 * @return 最新图片生成路径
	 */
	public static String drawImg(FlightInfo info) {

		//得到图片缓冲区
		FileInputStream logoFileInputStream = null;
		try {
			BufferedImage bi = new BufferedImage(ImageBuilder2.screenWidth, ImageBuilder2.screenHeight, BufferedImage.TYPE_INT_RGB);

			logoFileInputStream = new FileInputStream(ImageBuilder2.logoPath);
			BufferedImage logoImg = ImageIO.read(logoFileInputStream);
			//得到它的绘制环境(这张图片的笔)
			Graphics2D g2 = (Graphics2D) bi.getGraphics();
			//填充区域
			g2.fillRect(0, 0, ImageBuilder2.screenWidth, ImageBuilder2.screenHeight);
			//=======================logo start======================
			//设置颜色
			g2.setColor(Color.WHITE);
			g2.drawImage(logoImg, leftPadding, topPadding, logoWidth, logoHeight,null);
			g2.drawRect(0, 0, screenWidth - 1, screenHeight - 1);
			//=======================logo end======================

			//水平线
			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke(16.0f));
			g2.drawLine(leftPadding+10,topPadding+110,leftPadding+610,topPadding+110);

			//条形码图片
			g2.setColor(Color.WHITE);
			BufferedImage barImg = QRCodeUtil.createBarCode(info.getSeqNo(), 200, 50);
			g2.drawImage(barImg, barLeftPadding, topPadding+120, barWidth, barHeight,null);
			g2.setColor(Color.BLACK);
			int seqNoHeight =topPadding+120+barHeight;
			g2.setFont(new Font("黑体", Font.BOLD, 20));
			g2.drawString(info.getSeqNo(), leftPadding+240, seqNoHeight+20);
			g2.setStroke(new BasicStroke(4.0f));
			g2.drawLine(leftPadding+10,seqNoHeight+30,leftPadding+610,seqNoHeight+30);

			//货单+标签+保障等级
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("黑体", Font.BOLD, 30));
			g2.drawString("货单号/AWB NO.[CZ]", leftPadding, seqNoHeight+70);
			g2.setFont(new Font("黑体", Font.BOLD, 50));
			g2.drawString(info.getSeqNo(), leftPadding+20, seqNoHeight+130);
			g2.setFont(new Font("黑体", Font.BOLD, 50));
			g2.drawString("1", leftPadding+420, seqNoHeight+130);
			g2.drawString(info.getLevel(), leftPadding+550, seqNoHeight+130);

			//表格
			g2.setStroke(new BasicStroke(4.0f));
			g2.drawLine(leftPadding+10,seqNoHeight+150,leftPadding+610,seqNoHeight+150);//横线
			g2.setFont(new Font("黑体", Font.BOLD, 20));
			g2.drawString("件数/PCS.", leftPadding, seqNoHeight+190);
			g2.setFont(new Font("黑体", Font.BOLD, 50));
			g2.drawString(info.getPackageNum(), leftPadding+50, seqNoHeight+250);

			g2.drawLine(leftPadding+140,seqNoHeight+150,leftPadding+140,seqNoHeight+265);//竖线
			g2.setFont(new Font("黑体", Font.BOLD, 20));
			g2.drawString("货物重量/WTS.[kg]", leftPadding+155, seqNoHeight+190);
			g2.setFont(new Font("黑体", Font.BOLD, 50));
			g2.drawString(info.getWeight(), leftPadding+155, seqNoHeight+250);

			g2.drawLine(leftPadding+380,seqNoHeight+150,leftPadding+380,seqNoHeight+265);//竖线
			g2.setFont(new Font("黑体", Font.BOLD, 20));
			g2.drawString("本件重量/WT.OF THIS PC.", leftPadding+390, seqNoHeight+190);
			g2.setFont(new Font("黑体", Font.BOLD, 50));
			g2.drawString("0", leftPadding+420, seqNoHeight+250);

			g2.drawLine(leftPadding+10,seqNoHeight+265,leftPadding+610,seqNoHeight+265);//横线
			g2.setFont(new Font("黑体", Font.BOLD, 30));
			g2.drawString("始发站/FROM", leftPadding+10, seqNoHeight+300);
			g2.setFont(new Font("黑体", Font.BOLD, 50));
			g2.drawString(info.getStartStation(), leftPadding+120, seqNoHeight+350);
			g2.setFont(new Font("黑体", Font.BOLD, 30));
			g2.drawString("目的站/TO", leftPadding+320, seqNoHeight+300);
			g2.setFont(new Font("黑体", Font.BOLD, 50));
			g2.drawString(info.getDestinationStation(), leftPadding+400, seqNoHeight+350);


			// 图片上传后的路径
			String savePath = "F:/old.bmp";
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
		ImageBuilder2.FlightInfo flightInfo = new ImageBuilder2.FlightInfo();
		flightInfo.setStartStation("广州");
		flightInfo.setDestinationStation("上海");
		flightInfo.setLevel("A");
		flightInfo.setLabel("0004");
		flightInfo.setQrCode("784");
		flightInfo.setSeqNo("12345678");
		flightInfo.setPackageNum("102");
		flightInfo.setWeight("454545.1");

		String path =  ImageBuilder2.drawImg(flightInfo);
		log.info("图片生成成功: {}",path);
	}


	@Data
	static class FlightInfo{
		private String startStation; //始发
		private String destinationStation; //目的站
		private String level; //保障等级
		private String label; //标签
		private String qrCode; //二维码
		private String seqNo; //流水号
		private String packageNum; //行李数
		private String weight; //重量
	}
}
