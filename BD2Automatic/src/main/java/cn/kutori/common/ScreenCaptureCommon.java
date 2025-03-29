package cn.kutori.common;


import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 屏幕截图工具类
 */
public class ScreenCaptureCommon {

    // 获取屏幕截图的方法
    public static Mat getScreenShot() {
        try {
            // 获取屏幕的大小（全屏截图）
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            // 创建Robot对象并捕获屏幕图像
            Robot robot = new Robot();
            BufferedImage screenImage = robot.createScreenCapture(screenRect);
            // 将BufferedImage转为OpenCV的Mat对象
            return bufferedImageToMat(screenImage);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    // 将BufferedImage转为OpenCV Mat
    public static Mat bufferedImageToMat(BufferedImage image) {
        // 获取BufferedImage的像素数据
        int width = image.getWidth();
        int height = image.getHeight();
        // 创建OpenCV Mat对象，存储图像数据
        Mat mat = new Mat(height, width, CvType.CV_8UC3);
        // 将BufferedImage中的数据复制到Mat对象中
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                // 获取RGB颜色值
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;
                // 将RGB值放入Mat对象的每个像素点
                mat.put(y, x, new byte[]{(byte)blue, (byte)green, (byte)red});
            }
        }
        return mat;
    }
}