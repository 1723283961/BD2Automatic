package cn.kutori.config;

import cn.kutori.common.FindWindowByProcessCommon;
import com.sun.jna.platform.win32.WinDef;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 运行文件
 */
@Slf4j
public class SelectRunFileConfig {

    /**
     * 选择运行文件
     * @return 窗口句柄
     */
    public WinDef.HWND selectRunFile() throws Exception {
        Properties properties = new Properties();
        try (InputStream input = SelectRunFileConfig.class.getClassLoader().getResourceAsStream("config.properties")){
            if (input == null) {
                log.error("无法加载库");
                throw new Exception("Sorry, unable to find config.properties");
            }
            properties.load(input);
            // 替换为窗口引用的标题
            String windowApplication = properties.getProperty("config.SelectRunFileConfig");
            //抓取结果
            log.info("窗口引用的标题: " + windowApplication);
            // 获取窗口句柄
            WinDef.HWND hwnd = FindWindowByProcessCommon.getWindowByProcessName(windowApplication);
            if (hwnd == null) {
                log.error("未找到窗口：{}", windowApplication);
                throw new Exception("未找到窗口：" + windowApplication);
            }
            return hwnd;
        } catch (IOException e) {
            log.error("启动失败：{}", e.getMessage());
            throw new Exception(e);
        }
    }


}