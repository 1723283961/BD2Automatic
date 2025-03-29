package cn.kutori;

import cn.kutori.config.StartConfig;
import cn.kutori.method.Position;
import cn.kutori.method.SimulateClick;
import com.sun.jna.platform.win32.WinDef;
import org.opencv.core.Mat;

import java.util.Map;

import static cn.kutori.common.ScreenCaptureCommon.getScreenShot;

/**
 * Hello world!
 *
 */
public class BD2AutomaticDecompositionEquipmentApp {
    public static void main( String[] args ) {
        //加载自动配置类
        StartConfig startConfig = new StartConfig();
        //获取句柄
        WinDef.HWND Hwnd = startConfig.start();
        //获取图片路径，和匹配值
        Position position = new Position();
        //获取屏幕截图
        Mat mat = getScreenShot();

        try {
          Map<String,Integer> map = position.getXY(mat,"Max.png");
          SimulateClick.sendClick(map.get("x"),map.get("y"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(Hwnd);


    }
}
