package cn.kutori.GameUtils.Utils.ScriptRecordingTool;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 脚本转换工具
 */
@Slf4j
public class ScriptConverter {

    /**
     *  key:按鍵
     *  action:狀態 {
     *      press:按下
     *      release:松開
     * }
     *  time:相對時間（ms）
     */
    static class KeyAction {
        String key;
        String action;
        long time; // 相对时间（ms）
    }

    /**
     * 轉化時間戳
     * @param t 獲取通過脚本跑出來的String
     * @return 相對時間戳
     * @throws Exception 異常
     */
    private static long parseTime(String t) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        return sdf.parse(t).getTime();
    }

    /**
     * 轉化成json
     * @throws Exception
     */
    public void convertIntoJson(String fileName) throws Exception {
        Scanner scanner = new Scanner(new File(fileName + ".log"));

        Pattern pressPattern = Pattern.compile("按下 (.+?) @ (\\d{2}:\\d{2}:\\d{2}\\.\\d+)");
        Pattern releasePattern = Pattern.compile("松开 (.+?) \\(持续 (\\d+) ms\\) @ (\\d{2}:\\d{2}:\\d{2}\\.\\d+)");

        List<KeyAction> actions = new ArrayList<>();
        long baseTime = -1;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            Matcher m1 = pressPattern.matcher(line);
            Matcher m2 = releasePattern.matcher(line);

            if (m1.matches()) {
                String key = m1.group(1);
                long absTime = parseTime(m1.group(2));
                if (baseTime == -1) baseTime = absTime;

                KeyAction act = new KeyAction();
                act.key = key;
                act.action = "press";
                act.time = absTime - baseTime;
                actions.add(act);

            } else if (m2.matches()) {
                String key = m2.group(1);
                long duration = Long.parseLong(m2.group(2));
                long absTime = parseTime(m2.group(3));
                if (baseTime == -1) baseTime = absTime;

                KeyAction act = new KeyAction();
                act.key = key;
                act.action = "release";
                act.time = absTime - baseTime;
                actions.add(act);
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Files.writeString(new File(fileName).toPath(), gson.toJson(actions));
        log.info("转换完成，已保存到 {}", fileName + ".json");
    }
}
