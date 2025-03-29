package cn.kutori.config;


import java.net.URL;

/**
 *  加载OpenCV库
 */
public class GetOpenCVConfig {
    public GetOpenCVConfig() {
        System.out.println("加载OpenCV库");
        URL url = ClassLoader.getSystemResource("opencv/opencv_java4100.dll");
        System.load(url.getPath());
    }

}
