package com.noteshare.robot.test;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.RenderingHints.Key;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.sun.glass.events.KeyEvent;

/**
* @Title:
* @author  NoteShare
* @since   JDK1.8
* @history 2017年11月7日
*/
public class RobotAPITest {
    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        //截屏测试
        BufferedImage bimg = robot.createScreenCapture(new Rectangle(0, 0, 200, 600));
        ImageIO.write(bimg, "jpg", new File("D:/test.jpg"));
        //延迟毫秒
        robot.delay(1000);
        System.out.println("====");
        //Waits until all events currently on the event queue have been processed.
        //等待直到事件队列的所有事件都已经处理完毕
        robot.waitForIdle();
        //Sets whether this Robot automatically invokes waitForIdle after generating an event.
        //设置该机器人在生成事件后是否自动调用waitForIdle
        robot.setAutoWaitForIdle(true);
        //返回该机器人在生成事件后是否自动调用waitForIdle
        System.out.println(robot.isAutoWaitForIdle());
        //Returns the color of a pixel at the given screen coordinates
        //返回给定屏幕坐标下的像素的颜色
        Color color = robot.getPixelColor(926, 338);
        System.out.println(color.getRed());
        System.out.println(color.getGreen());
        System.out.println(color.getBlue());
        //Moves mouse pointer to given screen coordinates.
        //将鼠标移动到给定的屏幕坐标
        robot.mouseMove(700,500);
        //Presses one or more mouse buttons  --这个我也没测试出来？
        //按一个或多个鼠标按钮
        //robot.mousePress(1);
        //Releases one or more mouse buttons
        //robot.mouseRelease(1);
        //Rotates the scroll wheel on wheel-equipped mice.
        //转动轮子上装有轮子的老鼠
        robot.mouseWheel(10);
        //Sets the number of milliseconds this Robot sleeps after generating an event.
        //设置该机器人生成事件后休眠的毫秒数
        robot.setAutoDelay(1000);
        System.out.println("====");
        //Returns the number of milliseconds this Robot sleeps after generating an event.
        System.out.println(robot.getAutoDelay());
        //Presses a given key
        robot.keyPress(KeyEvent.VK_ENTER);
        //Releases a given key释放--这个要怎么测试呀？
        robot.keyRelease(KeyEvent.VK_ENTER);
        
        
    }
}
