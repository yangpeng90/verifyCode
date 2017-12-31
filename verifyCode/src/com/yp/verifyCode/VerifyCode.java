package com.yp.verifyCode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class VerifyCode {
	private int width = 70;
	private int height = 35;
	private Random r = new Random();
	private String[] fontNames  = {"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};
	private String codes  = "23456789abcdefghjkmnprtuyABCDEFGHJKMNPQRTUY";
	private Color bgColor  = new Color(255, 255, 255);
	private String text ;
	
	private Color randomColor() {
		int red = r.nextInt(150);
		int green = r.nextInt(150);
		int blue = r.nextInt(150);
		
		return new Color(red,green,blue);
	}
	
	private Font randomFont() {
		int index = r.nextInt(fontNames.length);
		String font = fontNames[index];
		int style = r.nextInt(4);
		int size = r.nextInt(9) + 20;
		return new Font(font,style,size);
	}
	
	private void randomLine(BufferedImage image) {
		int num = 4;
		Graphics2D g2 = image.createGraphics();
		for(int i = 0; i < num; i++) {
			int x1 = r.nextInt(width);
			int y1 = r.nextInt(height);
			int x2 = r.nextInt(width);
			int y2 = r.nextInt(height);
			g2.setStroke(new BasicStroke(1.5F));
			g2.setColor(Color.blue);
			g2.drawLine(x1, y1, x2, y2);
		}
	}
	
	private char randomChar() {
		int index = r.nextInt(codes.length());
		return codes.charAt(index);
	}
	
	private BufferedImage createImage() {
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bi.createGraphics();
		g2.setColor(this.bgColor);
		g2.fillRect(0, 0, width, height);
		return bi;
	}
	
	public BufferedImage getImage() {
		BufferedImage image = this.createImage();
		Graphics2D g2 = image.createGraphics();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 4; i++) {
			String s = String.valueOf(this.randomChar());
			float x = i * 1.0F * width / 4;
			sb.append(s);
			g2.setColor(randomColor());
			g2.setFont(randomFont());
			g2.drawString(s, x, height - 8);
		}
		this.text = sb.toString();
		this.randomLine(image);
		return image;
	}
	
	public String getText() {
		return this.text;
	}
	
	public static void imageOutput(BufferedImage image, OutputStream out) {
		try {
			ImageIO.write(image, "JPEG", out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
