package com.krista.spring.webflux.sample.controller;

import com.krista.extend.base.response.JsonResponse;
import com.krista.spring.webflux.sample.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * TestController
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/15 11:29
 */
@RestController
@RequestMapping(value = "test")
public class TestController {
    private static Map<String, User> userMap;
    private static int userCount = 10;

    static {
        userMap = new HashMap<>();
        for (int i = 0; i < userCount; i++) {
            String id = "" + i;
            userMap.put(id, new User(id, "user" + i, i));
        }
    }

    @GetMapping("get")
    public JsonResponse<User> get(String id) {
        User user = userMap.get(id);
        if (null == user) {
            return new JsonResponse<>(404);
        }

        return new JsonResponse<>(user);
    }

    @PostMapping("post")
    public JsonResponse<String> post(@RequestBody User user) {
        userCount++;
        String userId = userCount + "";
        userMap.put(userId, user);

        return new JsonResponse<>(userId);
    }

    @PostMapping("remove")
    public JsonResponse<String> remove(@RequestParam String userId) {
        if (userMap.containsKey(userId)) {
            userMap.remove(userId);

        }

        return new JsonResponse<>("");
    }

    @PostMapping("modify")
    public JsonResponse<String> modify(@RequestBody User user) {
        String userId = user.getId();
        userMap.put(userId, user);

        return new JsonResponse<>("");
    }
}
