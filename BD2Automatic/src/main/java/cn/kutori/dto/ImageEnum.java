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
    // 路径： \BD2Automatic\images\Equipmentdecomposition\...
    //装备制造max（建议选最低级的刷）
    Max("Max","Max.png"),
    //强化
    Strengthen("Strengthen","Strengthen.png"),
    //等待
    Wait("Wait","Wait.png"),
    //强化设定
    Choice("Choice","Choice.png"),
    //最后分解点击
    BreakDown("BreakDown","BreakDown.png"),
    //分解
    OnePieceDecomposition("OnePieceDecomposition","OnePieceDecomposition.png"),
    //等待出现
    WaitingAppear("WaitingAppear","WaitingAppear.png"),
    // 路径： \BD2Automatic\images\DoomsdayBook\...
    // Skip （跳过）
    Skip("Skip","Skip.png"),
    // Yes（是）
    Yes("Yes","Yes.png"),
    // ReChallenge（重试）
    ReChallenge("ReChallenge","ReChallenge.png");

    private final String name;
    private final String images;
}
