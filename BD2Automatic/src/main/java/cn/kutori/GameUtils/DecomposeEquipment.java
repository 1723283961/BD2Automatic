package cn.kutori.GameUtils;

import cn.kutori.dto.ImageEnum;
import cn.kutori.method.Position;
import cn.kutori.method.SimulateClick;
import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Mat;

import java.util.Map;

import static cn.kutori.common.ScreenCaptureCommon.getScreenShot;

/**
 * 分解装备逻辑封装
 */
@Slf4j
public class DecomposeEquipment {

    /**
     * 装备分解逻辑
     * @param map 装备分解位置
     * @param position 匹配位置点击方法
     * @throws Exception 抛出异常
     */
    public void toDecomposeEquipment(Map<String, Map<String,Integer>>map, Position position) throws Exception {
        //获取屏幕截图
        Mat mat = getScreenShot();
        map.put(ImageEnum.Max.getName(),position.getXY(mat,ImageEnum.Max.getImages()));
        map.put(ImageEnum.Choice.getName(), position.getXY(mat,ImageEnum.Choice.getImages()));

        //点击Max将制造个数变成max
        SimulateClick.sendClick(map.get(ImageEnum.Max.getName()).get("x"),map.get(ImageEnum.Max.getName()).get("y"));
        //等待跳转页面
        Thread.sleep(200);
        //点击强化设定
        SimulateClick.sendClick(map.get(ImageEnum.Choice.getName()).get("x"),map.get(ImageEnum.Choice.getName()).get("y"));
        //等待跳转页面
        Thread.sleep(200);
        //获取屏幕截图
        Mat mat1 = getScreenShot();
        //等待跳转页面
        Thread.sleep(200);
        //选择强加
        map.put(ImageEnum.Strengthen.getName(), position.getXY(mat1,ImageEnum.Strengthen.getImages()));
        SimulateClick.sendClick(map.get(ImageEnum.Strengthen.getName()).get("x"),map.get(ImageEnum.Strengthen.getName()).get("y"));
        //等待跳转页面
        Thread.sleep(500);
        //获取屏幕截图
        int sum = 1;
        //等待
        do {
            //获取屏幕截图
            Mat mat2 = getScreenShot();
            sum = position.getSum(mat2, ImageEnum.Wait.getImages());
        } while (sum != 0);

        int a;
        do {
            //获取屏幕截图
            Mat mat2 = getScreenShot();
            a = position.getSum(mat2, ImageEnum.WaitingAppear.getImages());
        } while (a == 1);

        Thread.sleep(2000);
        //获取屏幕截图
        Mat mat3 = getScreenShot();
        map.put(ImageEnum.OnePieceDecomposition.getName(), position.getXY(mat3,ImageEnum.OnePieceDecomposition.getImages()));
        SimulateClick.sendClick(map.get(ImageEnum.OnePieceDecomposition.getName()).get("x"),map.get(ImageEnum.OnePieceDecomposition.getName()).get("y"));
        Thread.sleep(500);
        // 分解
        Mat mat4 = getScreenShot();
        map.put(ImageEnum.BreakDown.getName(), position.getXY(mat4,ImageEnum.BreakDown.getImages()));
        SimulateClick.sendClick(map.get(ImageEnum.BreakDown.getName()).get("x"),map.get(ImageEnum.BreakDown.getName()).get("y"));
        //等待跳转页面
        Thread.sleep(2000);
        //用来点击取消东西
        SimulateClick.sendClick(map.get(ImageEnum.Choice.getName()).get("x"),map.get(ImageEnum.Choice.getName()).get("y"));
        Thread.sleep(2000);
    }
}
