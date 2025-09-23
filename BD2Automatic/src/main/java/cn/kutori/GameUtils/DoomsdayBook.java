package cn.kutori.GameUtils;

import cn.kutori.dto.ImageEnum;
import cn.kutori.method.Position;
import cn.kutori.method.SimulateClick;
import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Mat;

import java.util.Map;

import static cn.kutori.common.ScreenCaptureCommon.getScreenShot;

/**
 * 末日之书刷分
 */
@Slf4j
public class DoomsdayBook {

    /**
     * 魔兽重复点击流程
     * @param map 點擊位置
     * @param position 匹配位置点击方法
     * @throws Exception 抛出异常
     */
    public void toDoomsdayBook(Map<String, Map<String,Integer>>map, Position position) throws Exception {
        //获取屏幕截图
        Mat mat;
        Thread.sleep(5000);
        int sum = 1 ;
        //等待
        do {
            //获取屏幕截图
            Mat mat2 = getScreenShot();
            sum = position.getSum(mat2, ImageEnum.Skip.getImages());
            if(sum != 0){
                map.put(ImageEnum.Skip.getName(),position.getXY(mat2,ImageEnum.Skip.getImages()));
                SimulateClick.sendClick(map.get(ImageEnum.Skip.getName()).get("x"),map.get(ImageEnum.Skip.getName()).get("y"));
            }
        } while (sum == 0);
        boolean b = true;
        while (b){
            //获取屏幕截图
            mat = getScreenShot();
            int c = position.getSum(mat, ImageEnum.ReChallenge.getImages());
            if(c == 1){
                map.put(ImageEnum.ReChallenge.getName(),position.getXY(mat,ImageEnum.ReChallenge.getImages()));
                SimulateClick.sendClick(map.get(ImageEnum.ReChallenge.getName()).get("x"),map.get(ImageEnum.ReChallenge.getName()).get("y"));
                b = false;
            }
        }
        boolean c = true;
        while (c){
            //获取屏幕截图
            mat = getScreenShot();
            int d = position.getSum(mat, ImageEnum.Yes.getImages());
            if(d == 1){
                map.put(ImageEnum.Yes.getName(),position.getXY(mat,ImageEnum.Yes.getImages()));
                SimulateClick.sendClick(map.get(ImageEnum.Yes.getName()).get("x"),map.get(ImageEnum.Yes.getName()).get("y"));
                c = false;
            }
        }
    }

    /**
     * 刷分启动界面
     * @param map map坐标位置
     * @param position 匹配位置点击方法
     * @throws Exception 抛出异常
     */
    public void StarBook(Map<String, Map<String,Integer>>map, Position position) throws Exception {
        //获取屏幕截图
        Mat mat = new Mat();
        mat = getScreenShot();
        map.put(ImageEnum.BookStart.getName(),position.getXY(mat,ImageEnum.BookStart.getImages()));
        SimulateClick.sendClick(map.get(ImageEnum.BookStart.getName()).get("x"),map.get(ImageEnum.BookStart.getName()).get("y"));
    }

}
