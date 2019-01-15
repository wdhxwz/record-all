package com.krista.httpclients.sample.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * HttpClientSampleApplication
 * https://blog.csdn.net/zhuwukai/article/details/78644484
 * http://hc.apache.org/httpcomponents-client-ga/tutorial/html/index.html
 * https://blog.csdn.net/ron_2016/article/details/81587492
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/15 14:18
 */
public class HttpClientSampleApplication {
    private static int OK_STATUS = 200;
    private static String defaultCharset = "utf-8";

    public static void main(String[] args) throws IOException {
        RequestConfig requestConfig = RequestConfig.custom()
                // 连接超时：connectionTimeout-->指的是连接一个url的连接等待时间
                .setConnectTimeout(5000)
                // 读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间
                .setSocketTimeout(5000)
                // 从连接池获取连接的超时时间:ConnectionRequestTimeout
                .setConnectionRequestTimeout(5000)
                .build();

        HttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://127.0.0.1:7071/test/get?id=3");
        httpGet.setConfig(requestConfig);

        // Create a custom response handler
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
            @Override
            public String handleResponse(final HttpResponse response) throws IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status == OK_STATUS) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity, defaultCharset) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }
        };

        HttpResponse httpResponse = httpClient.execute(httpGet);
        if (httpResponse.getStatusLine().getStatusCode() == OK_STATUS) {
            HttpEntity resEntity = httpResponse.getEntity();
            String message = EntityUtils.toString(resEntity, defaultCharset);
            System.out.println(message);
        } else {
            System.out.println("请求失败");
        }

        String responseBody = httpClient.execute(httpGet, responseHandler);
        System.out.println(responseBody);
    }
}
