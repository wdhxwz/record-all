package com.krista.microservice.simple.movie;

import com.google.gson.JsonObject;
import com.krista.extend.utils.JsonUtil;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * RestTemplate测试
 *
 * @author wdhcxx
 */
public class RestTemplateTest {
    private static Logger logger = LoggerFactory.getLogger(RestTemplateTest.class);
    private RestTemplate restTemplate;
    private static String url = "http://127.0.0.1:8000/";

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testGet() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url + "/user/{1}", String.class, 3);
        printResponse(responseEntity);

        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("id", "2");
        responseEntity = restTemplate.getForEntity(url + "/user/{id}", String.class, paramMap);
        printResponse(responseEntity);
    }

    private void printResponse(ResponseEntity responseEntity){
//        logger.info(responseEntity.getBody().toString());
//        logger.info(responseEntity.getStatusCodeValue() + "");
//        logger.info(responseEntity.getStatusCode().toString());
//        logger.info(responseEntity.getHeaders().toString());
        logger.info(JsonUtil.toJson(responseEntity));
    }

    @Test
    public void testPost(){
        Map<String, String> userMap = new HashMap<>();
        userMap.put("username", "wangdh");
        userMap.put("name", "王东鸿");
        userMap.put("age", "23");
        userMap.put("balance", "0.00");

        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(url + "/user/add", userMap, String.class);
        printResponse(responseEntity);
    }

}
