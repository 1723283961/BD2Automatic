package cn.kutori.common;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

/**
 * 分辨率工具
 */
public class ResolvingPowerCommon {

    /**
     * 获取窗口缩放比例
     * @param hwnd 窗口句柄
     * @param originalWidth 截图时的原始宽度
     * @param originalHeight 截图时的原始高度
     * @return 比例 [widthRatio, heightRatio]
     */
    public double[] getWindowScale(WinDef.HWND hwnd, int originalWidth, int originalHeight) {
        WinDef.RECT rect = new WinDef.RECT();
        User32.INSTANCE.GetWindowRect(hwnd, rect);
        //获取现在图片的大小
        int currentWidth = rect.right - rect.left;
        int currentHeight = rect.bottom - rect.top;
        //对比例进行计算
        double scaleWidth = (double) currentWidth / originalWidth;
        double scaleHeight = (double) currentHeight / originalHeight;

        return new double[]{scaleWidth, scaleHeight};
    }

    /**
     * 调整匹配坐标
     * @param matchX 匹配结果的 X 坐标
     * @param matchY 匹配结果的 Y 坐标
     * @param scaleWidth 宽度比例
     * @param scaleHeight 高度比例
     * @return 调整后的 [adjustedX, adjustedY]
     */
    public int[] adjustCoordinates(int matchX, int matchY, double scaleWidth, double scaleHeight) {
        int adjustedX = (int) (matchX * scaleWidth);
        int adjustedY = (int) (matchY * scaleHeight);
        return new int[]{adjustedX, adjustedY};
    }

    /**
     * 获取到通过转化后先大小的图片的位置
     * @param hwnd 窗口句柄
     * @param matchX 匹配到的 X 坐标
     * @param matchY 匹配到的 Y 坐标
     * @param originalWidth 原始截图窗口宽度
     * @param originalHeight 原始截图窗口高度
     */
    public int[] clickMatch(WinDef.HWND hwnd, int matchX, int matchY, int originalWidth, int originalHeight) {
        // 获取窗口缩放比例
        double[] scale = getWindowScale(hwnd, originalWidth, originalHeight);
        double scaleWidth = scale[0];
        double scaleHeight = scale[1];

        // 调整匹配坐标
        int[] adjustedCoordinates = adjustCoordinates(matchX, matchY, scaleWidth, scaleHeight);
        int adjustedX = adjustedCoordinates[0];
        int adjustedY = adjustedCoordinates[1];
        return new int[]{adjustedX,adjustedY};
    }

}