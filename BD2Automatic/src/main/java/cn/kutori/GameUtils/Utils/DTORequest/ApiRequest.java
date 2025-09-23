package cn.kutori.GameUtils.Utils.DTORequest;

import lombok.Data;

/**
 * 接口请求类
 */
@Data
public class ApiRequest {
    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求体
     */
    private String body;
}
