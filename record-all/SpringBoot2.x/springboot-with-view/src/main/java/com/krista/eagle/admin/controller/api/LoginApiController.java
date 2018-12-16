package com.krista.eagle.admin.controller.api;

import com.krista.eagle.admin.model.dto.LoginDto;
import com.krista.extend.base.response.JsonResponse;
import com.krista.extend.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.krista.extend.base.response.JsonResponse.success;

/**
 * LoginApiController
 *
 * @author krista
 * @version V1.0
 * @since 2018/12/15 14:04
 */
@RestController
public class LoginApiController {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginApiController.class);

    @PostMapping("/user/login")
    public JsonResponse<Boolean> login(@RequestBody LoginDto loginDto) {
        LOGGER.info(JsonUtil.toJson(loginDto));

        JsonResponse<Boolean> jsonResponse = JsonResponse.success();
        jsonResponse.setData(true);

        return jsonResponse;
    }
}
