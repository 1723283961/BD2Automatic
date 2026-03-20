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

        int NumberOfWarnings = 0;
        while (!position.getHave(mat,ImageEnum.Max.getImages())){
            if(NumberOfWarnings >= 5){
                throw new Exception("请重新启动程序");
            }
            log.error("请调整bd2设置成格式要求大小查找不到按钮,请重试,5秒后重试,重试次数:{}",NumberOfWarnings);
            NumberOfWarnings++;
            mat = getScreenShot();
            Thread.sleep(5000);
        }
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
        mat = getScreenShot();
        //等待跳转页面
        Thread.sleep(200);
        //选择自动分解
        map.put(ImageEnum.Button.getName(), position.getXY(mat,ImageEnum.Button.getImages()));
        SimulateClick.sendClick(map.get(ImageEnum.Button.getName()).get("x"),map.get(ImageEnum.Button.getName()).get("y"));
        //选择强加
        map.put(ImageEnum.Strengthen.getName(), position.getXY(mat,ImageEnum.Strengthen.getImages()));
        SimulateClick.sendClick(map.get(ImageEnum.Strengthen.getName()).get("x"),map.get(ImageEnum.Strengthen.getName()).get("y"));
        //等待跳转页面
        Thread.sleep(500);

        //获取屏幕截图
        //等待点击一键分解
        do {
            mat = getScreenShot();
        } while (!position.getHave(mat, ImageEnum.Result.getImages()));
        Thread.sleep(200);

        //点击确认
        mat = getScreenShot();
        map.put(ImageEnum.Yes.getName(), position.getXY(mat,ImageEnum.Yes.getImages()));
        SimulateClick.sendClick(map.get(ImageEnum.Yes.getName()).get("x"),map.get(ImageEnum.Yes.getName()).get("y"));

        //获取屏幕截图
//        mat = getScreenShot();
//        map.put(ImageEnum.OnePieceDecomposition.getName(), position.getXY(mat,ImageEnum.OnePieceDecomposition.getImages()));
//        SimulateClick.sendClick(map.get(ImageEnum.OnePieceDecomposition.getName()).get("x"),map.get(ImageEnum.OnePieceDecomposition.getName()).get("y"));
//        Thread.sleep(500);
//
//        // 分解
//        mat = getScreenShot();
//        map.put(ImageEnum.BreakDown.getName(), position.getXY(mat,ImageEnum.BreakDown.getImages()));
//        SimulateClick.sendClick(map.get(ImageEnum.BreakDown.getName()).get("x"),map.get(ImageEnum.BreakDown.getName()).get("y"));
//        Thread.sleep(200);
//
//        // 等待分解完成界面出現
//        do {
//            mat = getScreenShot();
//        } while (!position.getHave(mat, ImageEnum.Again.getImages()));
//
//        Thread.sleep(200);
//        SimulateClick.sendClick(map.get(ImageEnum.Choice.getName()).get("x"),map.get(ImageEnum.Choice.getName()).get("y"));
        Thread.sleep(2000);
    }

}
