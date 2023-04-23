package com.robot.yzh.service.impl.auth;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.robot.yzh.domain.entity.RobotUrlEntity;
import com.robot.yzh.service.auth.IAuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Value("${robot.app.qq}")
    private Integer qq;

    @Value("${robot.url.address}")
    private String address;

    @Value("${robot.url.auth}")
    private String auth;

    @Value("${robot.url.binding}")
    private String binding;


    @Override
    public String verify(String verifyKey) {
        String url = address + auth;
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("verifyKey", verifyKey);
        String post = HttpUtil.post(url, JSONObject.toJSONString(paramMap));
        JSONObject jsonObject = JSONObject.parseObject(post);
        Integer code = jsonObject.getInteger("code");
        if (code!=0) {
            throw new RuntimeException("认证失败！");
        }
        // 你的session key
        String session = jsonObject.getString("session");

        binding(session,qq);

        return session;
    }

    @Override
    public boolean binding(String sessionKey, Integer qq) {
        String url = address + binding;

        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("sessionKey", sessionKey);
        paramMap.put("qq", qq);
        String post = HttpUtil.post(url, JSONObject.toJSONString(paramMap));
        JSONObject jsonObject = JSONObject.parseObject(post);
        Integer code = jsonObject.getInteger("code");
        if (code == 0) {
            return true;
        }
        return false;
    }
}
