package cn.kutori.GameUtils.Utils.ScriptRecordingTool;

import cn.kutori.config.SelectRunFileConfig;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 脚本錄製
 */
@Slf4j
public class GameKeyRecorder implements NativeKeyListener {

    private final Map<Integer, Long> keyPressTime = new HashMap<>();
    private final List<String> keyEvents = new ArrayList<>();
    // 運行
    private volatile boolean running = true;

    // 暫停
    private volatile boolean isRecording = false;

    private static final SimpleDateFormat FORMAT =
            new SimpleDateFormat("HH:mm:ss.SSS");

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        int keyCode = e.getKeyCode();
        String keyName = NativeKeyEvent.getKeyText(keyCode);

        if (keyCode == NativeKeyEvent.VC_ESCAPE) {
            running = false; // ESC 退出錄製
            return;
        }
        if (keyCode == NativeKeyEvent.VC_CAPS_LOCK || keyCode == NativeKeyEvent.VC_CLEAR) {
            return; // 忽略卻換屏幕
        }

        if (keyCode == NativeKeyEvent.VC_ENTER) {
            if (isRecording) {
                isRecording = false; // 開始錄製
                log.warn("暂停中...按 ENTER 继续");
            } else {
                if (isRecording) return;
                isRecording = true; // 開始錄製
                log.info("开始录制游戏操作，按 ENTER 暂停...");
            }
            return;
        }

        if (isRecording) {
            if (!keyPressTime.containsKey(keyCode)) {
                keyPressTime.put(keyCode, System.currentTimeMillis());
                keyEvents.add("按下 " + keyName + " @ " + FORMAT.format(new Date()));
            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        int keyCode = e.getKeyCode();
        String keyName = NativeKeyEvent.getKeyText(keyCode);

        if (keyPressTime.containsKey(keyCode)) {
            long duration = System.currentTimeMillis() - keyPressTime.get(keyCode);
            keyEvents.add("松开 " + keyName + " (持续 " + duration + " ms) @ " + FORMAT.format(new Date()));
            keyPressTime.remove(keyCode);
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {}

    public void saveLog(String filename) throws Exception {
        try (FileWriter fw = new FileWriter(filename)) {
            for (String line : keyEvents) {
                fw.write(line + "\n");
            }
        }
    }

    /**
     * 錄製監聽
     * @param fileName 文件名稱
     * @throws Exception 抛出異常
     */
    public void recordMap(String fileName) throws Exception {
        GameKeyRecorder recorder = new GameKeyRecorder();

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            log.error("无法注册全局键盘监听:{} ", ex.getMessage());
            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(recorder);
        log.info("开始录制，按ESC键结束录制");

        while (recorder.running) {
            Thread.sleep(200);
        }

        GlobalScreen.unregisterNativeHook();
        recorder.saveLog(fileName + ".log");
        log.info("录制完成，已保存到 {}", fileName + ".log");
    }
}
