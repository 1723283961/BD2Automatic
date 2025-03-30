package cn.kutori.config;

import lombok.extern.slf4j.Slf4j;

import java.net.URL;

/**
 *  加载OpenCV库
 */
@Slf4j
public class GetOpenCVConfig {

    public GetOpenCVConfig() {
        URL url = ClassLoader.getSystemResource("opencv/opencv_java4100.dll");
        System.load(url.getPath());
        log.info("加载OpenCV库:{}",url.getPath());
    }

}
