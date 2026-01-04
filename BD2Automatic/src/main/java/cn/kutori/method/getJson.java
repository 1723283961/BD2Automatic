package cn.kutori.method;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Properties;

/**
 * 獲取json
 */
@Slf4j
public class getJson {

    private final String jsonPath;

    /**
     * 读取文件配置
     */
    public getJson(String name) {

        Properties properties = new Properties();
        try (InputStream input = Position.class.getClassLoader().getResourceAsStream("config.properties")){
            if (input == null) {
                log.error("config.properties不存在");
                throw new RuntimeException("请检查config.properties是否存在");
            }
            properties.load(input);
            //获取图片路径
            jsonPath = System.getProperty("user.dir").replace("\\","/") + properties.getProperty("config.ImagePath") + name + "/";
            log.warn("提示,Json路径为：{}" , jsonPath);
            //获取匹配度
        } catch (IOException e) {
            log.error("读取失败：{}",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 脚本對應工具
     */
    static class KeyAction {
        String key;
        String action;
        long time;
    }

    /**
     * 脚本對應工具
     * @param key 按鍵
     * @return 對應的按鍵代碼
     */
    private static int getKeyCode(String key) {
        return switch (key.toUpperCase()) {
            case "W" -> KeyEvent.VK_W;
            case "A" -> KeyEvent.VK_A;
            case "S" -> KeyEvent.VK_S;
            case "D" -> KeyEvent.VK_D;
            default -> throw new IllegalArgumentException("未知按键: " + key);
        };
    }

    /**
     * 跑圖運行
     * @param fileName 脚本文件名
     * @throws Exception 抛出異常狀態
     */
    public void runMap(String fileName) throws Exception {
        // 對應的json的脚本
        String json = Files.readString(new File(jsonPath + fileName).toPath());
        List<KeyAction> actions = new Gson().fromJson(json, new TypeToken<List<KeyAction>>(){}.getType());

        Robot robot = new Robot();
        long start = System.currentTimeMillis();

        for (KeyAction act : actions) {
            long wait = act.time - (System.currentTimeMillis() - start);
            if (wait > 0) Thread.sleep(wait);

            int keyCode = getKeyCode(act.key);
            if ("press".equals(act.action)) {
                robot.keyPress(keyCode);
                log.info("执行：按下{} ",act.key);
            } else {
                robot.keyRelease(keyCode);
                log.info("执行：松开{} ",act.key);
            }
        }
        log.info("脚本执行完毕");
    }

}
