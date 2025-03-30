package cn.kutori.config;

import com.sun.jna.platform.win32.WinDef;
import lombok.extern.slf4j.Slf4j;

/**
 * 启动配置
 */
@Slf4j
public class StartConfig {

    /**
     * 启动配置
     * @return WinDef.HWND
     */
    public WinDef.HWND start() {
        log.info("启动脚本:{}", "BD2Automatic");
        new GetOpenCVConfig();
        SelectRunFileConfig selectRunFileConfig = new SelectRunFileConfig();
        try {
            return selectRunFileConfig.selectRunFile();
        } catch (Exception e) {
            log.error("启动脚本失败:{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
