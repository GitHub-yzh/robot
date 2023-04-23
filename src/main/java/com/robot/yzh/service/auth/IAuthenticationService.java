package com.robot.yzh.service.auth;

import org.springframework.stereotype.Service;

/**
 * 认证与会话
 */

public interface IAuthenticationService {

    /**
     * 认证-获取session
     * @param verifyKey
     * @return
     */
     String verify(String verifyKey);


    /**
     * 绑定
     * @param sessionKey 你的session key
     * @param qq Session将要绑定的Bot的qq号
     * @return true:成功 false：失败
     */
     boolean binding(String sessionKey, Integer qq);
}
