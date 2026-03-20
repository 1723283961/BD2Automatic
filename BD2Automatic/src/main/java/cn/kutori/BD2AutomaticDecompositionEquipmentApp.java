package cn.kutori;

import cn.kutori.config.StartConfig;
import cn.kutori.GameUtils.DecomposeEquipment;
import cn.kutori.method.Position;
import com.sun.jna.platform.win32.WinDef;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 自动刷粉
 */
@Slf4j
public class BD2AutomaticDecompositionEquipmentApp {

    public void start () throws Exception {
        //加载自动配置类
        StartConfig startConfig = new StartConfig();
        //获取句柄(其实可以不用但不想改，一些程序可以魔改成后台运行，但发现BD2不行就没改)
        WinDef.HWND Hwnd = startConfig.start();
        //获取图片路径，和匹配值
        Position position = new Position("Equipmentdecomposition");
        //创建保存点位
        Map<String,Map<String,Integer>>map = new HashMap<>();
        //调用装备分解方法
        DecomposeEquipment decomposeEquipment = new DecomposeEquipment();
        //循环次数
        int NOCycles = 60;
        int ans = 0;
        try {
            do {
                decomposeEquipment.toDecomposeEquipment(map, position);
                NOCycles--;
                log.info("完成次数：{}",++ans);
            }while (NOCycles != 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        BD2AutomaticDecompositionEquipmentApp bd2AutomaticDecompositionEquipmentApp = new BD2AutomaticDecompositionEquipmentApp();
        bd2AutomaticDecompositionEquipmentApp.start();
    }
}
