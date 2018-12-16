package com.krista.eagle.admin.controller.api;

import com.krista.extend.base.response.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * ProductApiController
 *
 * @author krista
 * @version V1.0
 * @since 2018/12/15 15:34
 */
@RestController
@RequestMapping("/product")
public class ProductApiController {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductApiController.class);

    private static Map<String, String> productMap = new HashMap<>();

    static {
        productMap.put("demo", "我是一个demo项目");
        productMap.put("krista", "Krista云");
        productMap.put("visit", "跟踪浏览系统");
    }

    @GetMapping("info")
    public JsonResponse<String> info(String productId) {
        LOGGER.info("productId = " + productId);
        JsonResponse<String> jsonResponse = JsonResponse.success();
        jsonResponse.setData(productMap.get(productId));

        return jsonResponse;
    }
}
