package cn.kutori.GameUtils.Utils;

import cn.kutori.GameUtils.Utils.DTORequest.ApiRequest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * 接口工具类
 */
public class InterfaceUtils {

    /**
     * 请求接口
     */
    public static String request(ApiRequest apiRequest)
            throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(apiRequest.getUrl()))
                .method(apiRequest.getMethod(), HttpRequest.BodyPublishers.ofString(apiRequest.getBody()))
                .build();
        //发送信息
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }


}
