package com.noteshare.encodeAuthCode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class AuthCode {
	
	public strictfp static void main(String[] args) throws IOException {
        double count = 100.0;
        long startTime = System.currentTimeMillis();
        char[] ignoreCodes = {'0', 'o', 'O', '1', 'I', 'L','l'};
        for (int i = 0; i < count; i++) {
            ImageIO.write(creatCodeImg(getAuthCode(4,ignoreCodes), 100, 30), "PNG", new FileOutputStream("D:\\code\\"+i+".png"));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("总用时:" + ((endTime - startTime) / 1000.0) + "s");
        System.out.println("平均用时:" + ((endTime - startTime) / count) + "ms/次");
    }
	
	/**
	  * @Title: creatCodeImg 
	  * @Description: 生成验证码图片流
	  * @param code 验证码字符串
	  * @param width 验证码图片宽度
	  * @param height 验证码图片高度
	  * @return BufferedImage 返回的验证码图片流
	 */
	public static BufferedImage creatCodeImg(String code, int width, int height) {
		Random ran = new Random();
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		// 消除线段的锯齿状边缘
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		char[] codes = code.toCharArray();
		// 创建字体
		Font fontFormat = new Font("Tahoma", Font.BOLD, 26);// 英文网站常用字体,为更好区分1IILOo等
		g.setColor(getRandomColor(220, 250));
		g.fillRect(0, 0, width, height);
		// 画出色条背景
		for (int i = 0; i < width; i++) {
			// 改变图形的当前颜色为随机生成的颜色
			g.setColor(getRandomColor(220, 250));
			// 画出背景图(圆角)前端已实现,效果优于这里
			//g.fillRoundRect(0, 0, width, height, 10, 10);
			//背景颜色条
			g.fillRect(i, 0, 1, height);
		}
		// 防止最后一个字超出图片边缘
		width = width - fontFormat.getSize() / 2;
		//将字符画到图片上
		for (int i = 0; i < codes.length; i++) {
			//随机缩放文字并将文字旋转指定角度
			//画一个字旋转缩放一次,旋转轴以当前所画的字的中心
			//改变画笔颜色范围，以防止和背景色混淆
			g.setColor(getRandomColor(100, 200));
			g.setFont(fontFormat);
			AffineTransform trans = new AffineTransform();
			// 旋转正负45度.以文字中心为轴
			trans.rotate((ran.nextBoolean() ? -1 : 1) * ran.nextInt(45)* Math.PI / 180, width * i / codes.length + fontFormat.getSize() / 2, height / 2);
			// 缩放文字
			float scaleSize = ran.nextFloat() + 0.8f;
			if (scaleSize > 1.1f)
				scaleSize = 1f;
			trans.scale(scaleSize, scaleSize);
			g.setTransform(trans);
			// 将文字花在图片上
			g.drawString(String.valueOf(codes[i]), width / codes.length * i
					+ 10, height / 2 + fontFormat.getSize() / 2);
		}
		int size = 0;
        for (int i = 0; i < size; i++) {
            g.setColor(getRandomColor(0, 100));
            g.drawLine(ran.nextInt(width - 1), ran.nextInt(height - 1), ran.nextInt(width - 1), ran.nextInt(height - 1));
        }
		int[] xPoints = new int[size];
		int[] yPoints = new int[size];
		for (int j = 0; j < size; j++) {
			xPoints[j] = ran.nextInt(width - 1);
			yPoints[j] = ran.nextInt(height - 1);
		}
//		 改变图形的当前颜色为随机生成的颜色
		g.setColor(getRandomColor(0, 100));
		g.drawPolyline(xPoints, yPoints, size);
        // 释放画笔
        g.dispose();
        return bi;
	}

	/**
	 * @Title: getRandomColor
	 * @Description: 获取s+Math.abs(e-s)范围内的颜色对象
	 * @param s rgb三色最小值， 最大值为s+Math.abs(e-s)的值，超过255则取默认最大值255
	 * @param e rgb最大值超过255则取默认最大值255
	 * @return Color
	 */
	private static Color getRandomColor(int s, int e) {
		Random random = new Random();
		if (s > 255)
			s = 255;
		if (e > 255)
			e = 255;
		int r = s + random.nextInt(Math.abs(e - s));
		int g = s + random.nextInt(Math.abs(e - s));
		int b = s + random.nextInt(Math.abs(e - s));
		return new Color(r, g, b);
	}

	/**
	 * @Title: getAuthCode
	 * @Description: 生成指定长度的验证码字符串
	 * @param len
	 *            验证码字符个数
	 * @param ignoreCodes
	 *            验证码中不应该包含的字符
	 * @return String 返回生成的验证码字符
	 */
	public static String getAuthCode(int len, char[] ignoreCodes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			char code = getRandomCode();
			for (int j = 0; j < ignoreCodes.length; j++) {
				if (code == ignoreCodes[j]) {
					i--;
					break;
				}
				// 生成的随机字符不包含在忽略字符中则就直接添加
				if (j == ignoreCodes.length - 1) {
					sb.append(code);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * @Title: getRandomCode
	 * @Description: 从A-Za-z0-9中获取一个随机字符
	 * @return char 返回获取到的随机字符
	 */
	private static char getRandomCode() {
		Random ran = new Random();
		char oneChar = (char) (ran.nextInt(26) + 'A');
		char twoChar = (char) (ran.nextInt(26) + 'a');
		char threeChar = (char) (ran.nextInt(10) + '0');
		switch (ran.nextInt(3) + 1) {
		case 1:
			return oneChar;
		case 2:
			return twoChar;
		case 3:
			return threeChar;
		default:
			return oneChar;
		}
	}
}
