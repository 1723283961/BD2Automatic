package cn.kutori.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * 照片枚举类
 */
@Getter
@AllArgsConstructor
public enum ImageEnum implements Serializable {
    //装备制造max（建议选最低级的刷）
    Max("Max","Max.png"),
    //确定
    Yes("Yes","Yes.png"),
    //强化
    Strengthen("Strengthen","Strengthen.png"),
    //等待
    Wait("Wait","Wait.png"),
    //强化设定
    Choice("Choice","Choice.png"),
    //一键分解按钮
    BreakDown("BreakDown","BreakDown.png");

    private final String name;
    private final String images;
}
