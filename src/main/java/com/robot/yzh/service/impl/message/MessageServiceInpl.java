package com.robot.yzh.service.impl.message;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.robot.yzh.domain.entity.RobotUrlEntity;
import com.robot.yzh.service.gpt.IGptService;
import com.robot.yzh.service.message.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MessageServiceInpl implements IMessageService {

    @Autowired
    private IGptService gptService;

    @Value("${robot.url.address}")
    private String address;

    @Value("${robot.url.fetchMessage}")
    private String fetchMessage;

    @Value("${robot.url.sendFriendMessage}")
    private String sendFriendMessage;

    @Override
    public void NewMessgae(String sessionKey) {
        while (true){
            String url = address +fetchMessage + "?sessionKey=" + sessionKey +"&count="+100;
            String res = HttpUtil.get(url);
            JSONObject jsonObject = JSONObject.parseObject(res);
            JSONArray data = jsonObject.getJSONArray("data");
            if (data.size()>0) {
                for (Object item : data) {
                    JSONObject obj = JSONObject.parseObject(JSONObject.toJSONString(item));
                    String type = obj.getString("type");

                    // 好友消息
                    if (type.equals("FriendMessage")) {

                        JSONArray messageChain = obj.getJSONArray("messageChain");
                        JSONObject messageChainObj = messageChain.getJSONObject(1);
                        String messageType = messageChainObj.getString("type");
                        if (messageType.equals("Plain")) {
                            String text = messageChainObj.getString("text");
                            String gptText = gptService.gpt4(text);
                            if (gptText == "") {
                                break;
                            }
                            // 好友内容
                            JSONObject sender = obj.getJSONObject("sender");
                            Long id = sender.getLong("id");
                            HashMap<String, String> map = new HashMap<>();
                            map.put("type","Plain");
                            map.put("text",gptText);

                            List<Object> list = new ArrayList<>();
                            list.add(map);

                            HashMap<String, Object> paramMap = new HashMap<>();
                            paramMap.put("sessionKey", sessionKey);
                            paramMap.put("target", id);
                            paramMap.put("messageChain", list);
                            HttpUtil.post(address + sendFriendMessage, JSONObject.toJSONString(paramMap));
                        }

                    }
                }
            }
        }

    }
}
