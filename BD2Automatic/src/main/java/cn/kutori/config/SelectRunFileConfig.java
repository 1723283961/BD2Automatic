package cn.kutori.config;

import cn.kutori.common.FindWindowByProcessCommon;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 运行文件
 */
public class SelectRunFileConfig {

    /**
     * 选择运行文件
     * @return 窗口句柄
     */
    public WinDef.HWND selectRunFile() throws Exception {
        Properties properties = new Properties();
        try (InputStream input = SelectRunFileConfig.class.getClassLoader().getResourceAsStream("config.properties")){
            if (input == null) {
                throw new Exception("Sorry, unable to find config.properties");
            }
            properties.load(input);
            // 替换为窗口引用的标题
            String windowApplication = properties.getProperty("config.SelectRunFileConfig");
            //抓取结果
            System.out.println("windowApplication value: " + windowApplication);
            // 获取窗口句柄
            WinDef.HWND hwnd = FindWindowByProcessCommon.getWindowByProcessName(windowApplication);
            if (hwnd == null) {
                throw new Exception("未找到窗口：" + windowApplication);
            }
            return hwnd;
        } catch (IOException e) {
            throw new Exception(e);
        }
    }


}