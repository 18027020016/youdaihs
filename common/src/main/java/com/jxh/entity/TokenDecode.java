package com.jxh.entity;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

/****
 * 用户登录后，数据会封装到SecurityContextHolder.getContext().getAuthentication()里面
 * 解密令牌信息
 */
public class TokenDecode {
    //公钥
    private static final String PUBLIC_KEY = "public.key";

    private static String publickey = "";

    /****
     * 获取公钥
     * @return
     */
    public String getPublicKey() {
        if (StringUtils.isNotEmpty(publickey)) {
            return publickey;
        }

        ClassPathResource resource = new ClassPathResource(PUBLIC_KEY);
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
            BufferedReader br = new BufferedReader(inputStreamReader);
            publickey = br.lines().collect(Collectors.joining("\n"));
            return publickey;
        } catch (IOException e) {
            return null;
        }
    }

    /****
     * 解码令牌
     * @param token
     * @return
     */
    public Map<String, String> decodeToken(String token) {
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(getPublicKey()));

        //获取jwt内容
        String claims = jwt.getClaims();
        return JSON.parseObject(claims, Map.class);
    }

    /****
     * 获取用户信息
     * @return
     */
    public Map<String, String> getUserInfo() {
        //获取授权信息
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        //解码令牌
        return decodeToken(details.getTokenValue());
    }
}
