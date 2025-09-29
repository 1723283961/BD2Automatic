package cn.kutori;

import cn.kutori.GameUtils.DoomsdayBook;
import cn.kutori.config.StartConfig;
import cn.kutori.method.Position;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 魔兽自动点击
 */
@Slf4j
public class BD2AutomaticDoomsdayBook {
    public void start () throws Exception {
        StartConfig startConfig = new StartConfig();
        startConfig.start();
        Position position = new Position("DoomsdayBook");
        Map<String, Map<String,Integer>>map = new HashMap<>();
        DoomsdayBook doomsdayBook = new DoomsdayBook();
        doomsdayBook.StarBook(map, position);
        int NOCycles = 10000;
        int ans = 0;
        try {
            while (NOCycles-- > 0) {
                doomsdayBook.toDoomsdayBook(map, position);
                NOCycles--;
                log.info("完成次数：{}",++ans);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        BD2AutomaticDoomsdayBook bd2AutomaticDoomsdayBook = new BD2AutomaticDoomsdayBook();
        bd2AutomaticDoomsdayBook.start();
    }
}
