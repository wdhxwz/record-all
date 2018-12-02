package com.krista.java.test.jwt;

        import com.krista.extend.utils.JsonUtil;
        import io.jsonwebtoken.Claims;

public class JwtApplication {
    public static void main(String[] args) throws InterruptedException {
        User user = new User();
        user.setId("123");
        user.setPassword("1q2w3e4r5t6y7u8i9o0p");
        user.setUserName("krista");

        String token = JwtUtil.createJWT(1, user);
        // Thread.sleep(1000);

        System.out.println(token);

        user.setPassword("123");
        // jwt已过期
        // 密钥错误
        boolean b = JwtUtil.isVerify(token, user);
        System.out.println(b);

        Claims claims = JwtUtil.parseJWT(token, user);
        System.out.println(JsonUtil.toJson(claims));
    }
}
