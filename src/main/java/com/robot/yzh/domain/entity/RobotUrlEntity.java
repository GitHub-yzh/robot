package com.robot.yzh.domain.entity;


import lombok.Data;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;

/**
 * 路径
 */

public class RobotUrlEntity {
    @Value("${robot.url.address}")
    String address;

    @Value("${robot.url.auth}")
    String auth;

    @Value("${robot.url.binding}")
    String binding;

    @Value("${robot.url.fetchMessage}")
    String fetchMessage;

    @Value("${robot.url.sendFriendMessage}")
    String sendFriendMessage;

    public RobotUrlEntity() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getFetchMessage() {
        return fetchMessage;
    }

    public void setFetchMessage(String fetchMessage) {
        this.fetchMessage = fetchMessage;
    }

    public String getSendFriendMessage() {
        return sendFriendMessage;
    }

    public void setSendFriendMessage(String sendFriendMessage) {
        this.sendFriendMessage = sendFriendMessage;
    }
}
