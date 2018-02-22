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

/**
 * 登录用的工具类,如验证码图片生成
 *
 * @author Drizzly
 */
public class LoginUtilAuthCode {

    /**
     * 测试效率
     * <p/>
     * 总用时:62.098s<br>
     * 平均用时:0.62098ms/次
     *
     * @param args
     */
    public strictfp static void main(String[] args) throws IOException {

        double count = 100.0;

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {

            ImageIO.write(createCodeImage(getRandCode(4), 100, 30), "PNG", new FileOutputStream("D:\\code\\"+i+".png"));

        }

        long endTime = System.currentTimeMillis();

        System.out.println("总用时:" + ((endTime - startTime) / 1000.0) + "s");

        System.out.println("平均用时:" + ((endTime - startTime) / count) + "ms/次");
    }

    /**
     * 创建验证码图片
     *
     * @param code
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage createCodeImage(String code, int width, int height) {
        Random random = new Random();
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) bi.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        char[] codes = code.toCharArray();

        // 创建字体
        Font fontFormat = new Font("Tahoma", Font.BOLD, 26);//英文网站常用字体,为更好区分1IILOo等

        g.setColor(getRandColor(220, 250));

        g.fillRect(0, 0, width, height);

		for (int i = 0; i < width; i++) {

			// 改变图形的当前颜色为随机生成的颜色
			g.setColor(getRandColor(220, 250));
	
			// // 画出背景图(圆角)前端已实现,效果优于这里
			// g.fillRoundRect(0, 0, width, height,10 , 10);
	
			// 画出背景图
			g.fillRect(i, 0, 1, height);
		
		}


        // 防止最后一个字超出图片边缘
        width = width - fontFormat.getSize() / 2;

        for (int i = 0; i < codes.length; i++) {

			/*
			 * 随机缩放文字并将文字旋转指定角度
			 * 
			 * 画一个字旋转缩放一次,旋转轴以当前所画的字的中心
			 */

            // 将文字旋转指定角度

            // 改变图形的当前颜色为随机生成的颜色
            g.setColor(getRandColor(100, 200));
            g.setFont(fontFormat);
            AffineTransform trans = new AffineTransform();
            // 旋转正负45度.以文字中心为轴
            trans.rotate((random.nextBoolean() ? -1 : 1) * random.nextInt(45) * Math.PI / 180, width * i / codes.length + fontFormat.getSize() / 2, height / 2);
            // 缩放文字
            float scaleSize = random.nextFloat() + 0.8f;
            if (scaleSize > 1.1f)
                scaleSize = 1f;
            trans.scale(scaleSize, scaleSize);
            g.setTransform(trans);
            // 将文字花在图片上
            g.drawString(String.valueOf(codes[i]), width / codes.length * i + 10, height / 2 + fontFormat.getSize() / 2);
        }

        int size = 0;
        for (int i = 0; i < size; i++) {
            g.setColor(getRandColor(0, 100));
            g.drawLine(random.nextInt(width - 1), random.nextInt(height - 1), random.nextInt(width - 1), random.nextInt(height - 1));
        }
		int[] xPoints = new int[size];
		int[] yPoints = new int[size];
		for (int j = 0; j < size; j++) {
			xPoints[j] = random.nextInt(width - 1);
			yPoints[j] = random.nextInt(height - 1);
		}
//		 改变图形的当前颜色为随机生成的颜色
		g.setColor(getRandColor(0, 100));
		g.drawPolyline(xPoints, yPoints, size);

        // 释放画笔
        g.dispose();
        return bi;
    }

    /**
     * 获取随机颜色
     *
     * @param s 开始色0~255
     * @param e 结束色0~255
     * @return
     */
    public static Color getRandColor(int s, int e) {
        Random random = new Random();
        if (s > 255)
            s = 255;
        if (e > 255)
            e = 255;
        int r = s + random.nextInt(e - s);
        int g = s + random.nextInt(e - s);
        int b = s + random.nextInt(e - s);
        return new Color(r, g, b);
    }

    /**
     * 生成验证码
     *
     * @param len 验证码长度
     * @return
     */
    public static String getRandCode(int len) {
        StringBuffer sb = new StringBuffer();
        char[] ignore = new char[]{'0', 'o', 'O', '1', 'I', 'L','l'};
        for (int i = 0; i < len; i++) {
            char charset = getRandCode();
            for (int j = 0; j < ignore.length; j++) {
                if (charset == ignore[j]){
                    i--;
                    break;
                }
                //最后一个都不有 break ,则不忽略
                if(j == ignore.length-1)
                    sb.append(charset);
            }
        }
        return sb.toString();
    }

    /**
     * 创建单个随机字符
     *
     * @return
     */
    public static char getRandCode() {
        Random rand = new Random();

        char oneCode = (char) (rand.nextInt(26) + 'A');
        char twoCode = (char) (rand.nextInt(26) + 'a');
        char threeCode = (char) (rand.nextInt(9) + '0');

        switch (rand.nextInt(3) + 1) {
            case 1:
                return oneCode;
            case 2:
                return twoCode;
            case 3:
                return threeCode;
            default:
                return oneCode;
        }
    }
}
