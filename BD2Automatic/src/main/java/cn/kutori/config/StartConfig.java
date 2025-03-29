package cn.kutori.config;

import com.sun.jna.platform.win32.WinDef;

/**
 * 启动配置
 */
public class StartConfig {

    public WinDef.HWND start() {
        System.out.println("启动脚本");
        new GetOpenCVConfig();
        SelectRunFileConfig selectRunFileConfig = new SelectRunFileConfig();
        try {
            return selectRunFileConfig.selectRunFile();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
