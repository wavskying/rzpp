package com.wav.rzpp.utils;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wav.rzpp.entity.RzRole;
import com.wav.rzpp.entity.RzUser;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author: hbw
 **/
public class JWTUtil {
    /**
     * 自定义加密串
     */
    private final static String STR_SECRET = "2817253626hbw@gmail.com";
    private final static String STR_ISSUSER = "https://api.hbw.inner.cn";
    private final static String STR_AUDIENCE = "https://api.hbw.inner.cn";
    private final static long TIME_EXPIRE = 1000 * 60 * 60 * 24; // 天的时间戳
    //private final static long TIME_EXPIRE = 60000; // 1年的时间戳

    /**
     * JWT 加密
     */
    public static String encode(String jsonData) {
        try {
            long timeNow = System.currentTimeMillis();

            return JWT.create()
                    .withIssuer(STR_ISSUSER)
                    .withAudience(STR_AUDIENCE)
                    .withIssuedAt(new Date(timeNow))
                    .withExpiresAt(new Date(System.currentTimeMillis() + TIME_EXPIRE))
                    .withClaim("data", jsonData)
                    .sign(Algorithm.HMAC256(STR_SECRET));

        } catch (UnsupportedEncodingException | JWTCreationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JWT 解密
     * <p>
     * System.out.println("user_id:" + jwt.getClaim("user_id").asString());
     */
    public static JSONObject decode(final String jwttoken) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(STR_SECRET)).withIssuer(STR_ISSUSER).build(); // Reusable verifier instance
            DecodedJWT jwt = verifier.verify(jwttoken);
            return JSONObject.parseObject(jwt.getClaim("data").asString());
        } catch (JWTVerificationException | IllegalArgumentException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过用户对象和角色对象构造token
     *
     * @param rzUser 用户对象
     * @param rzRole 角色对象
     * @return
     */
    public static String getToken(RzUser rzUser, RzRole rzRole) {
        JSONObject info = new JSONObject();
        info.put("userId", rzUser.getUserId());
        info.put("username", rzUser.getUsername());
        info.put("roleId", rzRole.getRoleId());
        info.put("roleName", rzRole.getRoleName());
        return encode(info.toJSONString());
    }

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "张三");
        jsonObject.put("sex", "男");
        String encode = encode(jsonObject.toJSONString());
        System.out.println(encode);
        System.out.println(decode(encode));
    }
}
