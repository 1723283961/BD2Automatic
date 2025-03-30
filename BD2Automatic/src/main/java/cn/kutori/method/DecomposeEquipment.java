package cn.kutori.method;

import cn.kutori.dto.ImageEnum;
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
    public void toDecomposeEquipment(Map<String, Map<String,Integer>>map,Position position) throws Exception {
        //获取屏幕截图
        Mat mat = getScreenShot();
        //执行一边在对点位进行缓存处理操作
        //点击Max将制造个数变成max
        map.put(ImageEnum.Max.getName(),position.getXY(mat,ImageEnum.Max.getImages()));
        SimulateClick.sendClick(map.get(ImageEnum.Max.getName()).get("x"),map.get(ImageEnum.Max.getName()).get("y"));
        //等待跳转页面
        Thread.sleep(200);
        //点击强化设定
        map.put(ImageEnum.Choice.getName(), position.getXY(mat,ImageEnum.Choice.getImages()));
        SimulateClick.sendClick(map.get(ImageEnum.Choice.getName()).get("x"),map.get(ImageEnum.Choice.getName()).get("y"));
        //等待跳转页面
        Thread.sleep(200);
        //获取屏幕截图
        Mat mat1 = getScreenShot();
        //选择一键分解
        map.put(ImageEnum.BreakDown.getName(), position.getXY(mat1,ImageEnum.BreakDown.getImages()));
        SimulateClick.sendClick(map.get(ImageEnum.BreakDown.getName()).get("x"),map.get(ImageEnum.BreakDown.getName()).get("y"));
        //等待跳转页面
        Thread.sleep(200);
        //选择强加
        map.put(ImageEnum.Strengthen.getName(), position.getXY(mat1,ImageEnum.Strengthen.getImages()));
        SimulateClick.sendClick(map.get(ImageEnum.Strengthen.getName()).get("x"),map.get(ImageEnum.Strengthen.getName()).get("y"));
        //等待跳转页面
        Thread.sleep(500);
        //获取屏幕截图
        int sum = 1;
        int wait = 10000 * 6 * 7;
        //等待
        do {
            //获取屏幕截图
            Mat mat2 = getScreenShot();
            sum = position.getSum(mat2, ImageEnum.Wait.getImages());
            log.info("大约还需要等待：{} 秒",(wait - 10000 * 6));
            Thread.sleep(10000 * 6);
        } while (sum != 0);
        Mat mat3 = getScreenShot();
        //选择确定
        map.put(ImageEnum.Yes.getName(), position.getXY(mat3,ImageEnum.Yes.getImages()));
        SimulateClick.sendClick(map.get(ImageEnum.Yes.getName()).get("x"),map.get(ImageEnum.Yes.getName()).get("y"));
        //等待跳转页面
        Thread.sleep(200);
    }
}
