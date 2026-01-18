package cn.kutori.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Json枚举类
 */
@Getter
@AllArgsConstructor
public enum JsonEnum {

    Map7_2("Map7_2","/Json/Map1/Map1.json");

    private final String name;

    private final String path;



}
