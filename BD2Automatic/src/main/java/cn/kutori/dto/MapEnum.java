package cn.kutori.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * 照片枚举类
 */
@Getter
@AllArgsConstructor
public enum MapEnum implements Serializable {
    // 路径： \BD2Automatic\images\RunningMap\..
    // 地圖7
    Map7("Map7","/Map/Map7/Map7.png"),
    Map7_1("Map7_1","/Map/Map7/Map7_1.png"),
    Map7_2("Map7_2","/Map/Map7/Map7_2.png"),
    // 地圖8
    Map8("Map8","/Map/Map8/Map8.png"),
    Map8_1("Map8_1","/Map/Map8/Map8_1.png"),
    Map8_2("Map8_2","/Map/Map8/Map8_2.png"),


    // 角色技能
    /**
     * 技能組優先順序 探索 ——> 吸收材料 ——> 收集小精靈
     */
    // 開啓探索
    CharacterExploration("CharacterExploration","/Role/CharacterExploration"),
    // 吸收材料
    Absorb_1("Absorb_1","/Role/Absorb_1"),
    Absorb_2("Absorb_2","/Role/Absorb_2"),
    // 收集小精靈
    SummoningSkills_1("SummoningSkills_1","/Role/SummoningSkills_1");



    private final String name;
    private final String images;

}
