package com.krista.nacos.config.longpolling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;

/**
 * AbstractBootstrap
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/8 11:47
 */
public class AbstractBootstrap {
    public static void main(String[] args) throws IOException {
        AbstractBootstrap bootstrap = new AbstractBootstrap();
        // 发起longpolling
        bootstrap.poll();
        System.in.read();
    }

    /**
     * 异步URL
     */
    protected static final String ASYNC_URL = "http://127.0.0.1:8989/longPolling";

    private final AtomicLong sequence = new AtomicLong();
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBootstrap.class);

    protected void poll() {
        // 循环执行，保证每次longpolling结束，再次发起longpolling
        while (!Thread.interrupted()) {
            doPoll();
        }
    }

    protected void doPoll() {
        System.out.println("第" + (sequence.incrementAndGet()) + "次 longpolling");
        long startMillis = System.currentTimeMillis();
        HttpURLConnection connection = null;
        try {
            URL getUrl = new URL(ASYNC_URL);
            connection = (HttpURLConnection) getUrl.openConnection();

            //50s作为长轮询超时时间
            connection.setReadTimeout(50000);
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Accept-Charset", "application/json;charset=UTF-8");
            connection.connect();

            if (200 == connection.getResponseCode()) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
                    StringBuilder result = new StringBuilder(256);
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    LOGGER.info("结果 {}", result);
                }
            }
        } catch (IOException e) {
            LOGGER.error("request failed");
        } finally {
            long elapsed = (System.currentTimeMillis() - startMillis) / 1000;
            LOGGER.info("connection close;elapse " + elapsed + "s");
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
