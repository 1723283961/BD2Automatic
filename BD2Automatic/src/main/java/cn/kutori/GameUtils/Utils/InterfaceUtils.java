package cn.kutori.GameUtils.Utils;

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
    public static String request(String url, String method, String body)
            throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .method(method, HttpRequest.BodyPublishers.ofString(body))
                .build();
        //发送信息
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }


}
