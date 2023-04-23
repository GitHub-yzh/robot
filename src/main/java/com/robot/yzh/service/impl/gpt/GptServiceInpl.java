package com.robot.yzh.service.impl.gpt;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.robot.yzh.service.gpt.IGptService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GptServiceInpl implements IGptService {
    @Override
    public String gpt4(String text) {
        try{
            List<Object> list =new ArrayList<>();
            HashMap<String, Object> map = new HashMap<>();
            map.put("role","user");
            map.put("content",text);
            list.add(map);
            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("model", "gpt-3.5-turbo");
            paramMap.put("messages", list);
//
//        String post = HttpUtil.post("https://api.openai.com/v1/completions", JSONObject.toJSONString(paramMap))
//                .header();
            String post = HttpRequest.post("https://api.openai.com/v1/chat/completions")
                    .setHttpProxy("127.0.0.1", 10810)
                    .header("Authorization", "Bearer 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")
                    .body(JSONObject.toJSONString(paramMap))
                    .execute().body();
            JSONObject jsonObject = JSONObject.parseObject(post);
            JSONArray choices = jsonObject.getJSONArray("choices");
            for (Object choice : choices) {
                JSONObject obj = JSONObject.parseObject(JSONObject.toJSONString(choice));
                JSONObject message = obj.getJSONObject("message");
                String content = message.getString("content");
                return content;
            }
        }catch (Exception e){
            gpt4(text);
        }

        return "";
    }
}
