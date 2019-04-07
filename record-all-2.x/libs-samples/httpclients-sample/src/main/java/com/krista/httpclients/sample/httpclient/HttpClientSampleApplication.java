package com.krista.httpclients.sample.httpclient;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

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
    private final static int MAX_RETRY_TIMES = 5;

    public static void main(String[] args) throws IOException {
        RequestConfig requestConfig = RequestConfig.custom()
                // 连接超时：connectionTimeout-->指的是连接一个url的连接等待时间
                .setConnectTimeout(5000)
                // 读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间
                .setSocketTimeout(5000)
                // 从连接池获取连接的超时时间:ConnectionRequestTimeout
                .setConnectionRequestTimeout(5000)
                .build();

        // HttpClient是线程安全的
        // CloseableHttpClient是需要进行资源释放的
        // IO是阻塞的
        HttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://127.0.0.1:7071/test/get?id=3");
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("header1","header1-value");

        // 响应处理
        ResponseHandler<String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status == OK_STATUS) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity, defaultCharset) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };

        HttpResponse httpResponse = httpClient.execute(httpGet);
        Header[] headers = httpResponse.getHeaders("header1");
        if (httpResponse.getStatusLine().getStatusCode() == OK_STATUS) {
            HttpEntity resEntity = httpResponse.getEntity();
            String message = EntityUtils.toString(resEntity, defaultCharset);
            System.out.println(message);
        } else {
            System.out.println("请求失败");
        }

        String responseBody = httpClient.execute(httpGet, responseHandler);
        System.out.println(responseBody);

        // 文件上传
        FileEntity fileEntity = new FileEntity(new File(""));

        // Form表单形式请求
        // param1=value1&param2=value2
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        formParams.add(new BasicNameValuePair("param1", "value1"));
        formParams.add(new BasicNameValuePair("param2", "value2"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams);
        HttpPost httpPostWithForm = new HttpPost("http://localhost/handler.do");
        httpPostWithForm.setEntity(entity);

        // 内容分块
        StringEntity stringEntity = new StringEntity("important message",
                ContentType.create("plain/text", Consts.UTF_8));
        stringEntity.setChunked(true);
        HttpPost httpPostWithString = new HttpPost("http://localhost/acrtion.do");
        httpPostWithString.setEntity(entity);

        // 请求重试
        HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount,
                                        HttpContext httpContext) {
                // 大于最大重试次数
                if (executionCount >= MAX_RETRY_TIMES) {
                    return false;
                }
                if (exception instanceof InterruptedIOException) {
                    // Timeout
                    return false;
                }
                if (exception instanceof UnknownHostException) {
                    // Unknown host
                    return false;
                }
                if (exception instanceof SSLException) {
                    // SSL handshake exception
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext.adapt(httpContext);
                HttpRequest request = clientContext.getRequest();

                // idempotent 幂等
                boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);

                return idempotent;
            }
        };

        // 设置连接池
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // 最大总连接数 200
        cm.setMaxTotal(200);
        // 将每条路由的默认最大连接数设置为20
        cm.setDefaultMaxPerRoute(20);

        CloseableHttpClient httpclient = HttpClients.custom()
                .setRetryHandler(retryHandler)
                .setConnectionManager(cm)
                .build();
    }
}
