package com.robot.yzh.controller;

import com.robot.yzh.common.api.CommonResult;
import com.robot.yzh.domain.dto.RobotCallBackDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杨峥辉
 * @version v1.0.0
 * @Package : com.robot.yzh.robot.controller
 * @Description : 接收机器人回调的消息
 * @Create on : 2023/3/24 11:00
 **/
@RestController
@RequestMapping("/robotCallBack")
public class RobotCallBackController {
    private Logger LOGGER = LoggerFactory.getLogger(RobotCallBackController.class);

    /**
     * 机器人回调
     * @param robotCallBackDto
     * @return
     */
    @RequestMapping("/callBack")
    public CommonResult RobotCallBack(RobotCallBackDto robotCallBackDto){
        LOGGER.info("回调信息：{}",robotCallBackDto);
        // 回调事件类型
        String type = robotCallBackDto.getType();
        if ("D0001".equals(type)) {
            // 注入成功
            LOGGER.info("注入成功");
        } else if ("D0002".equals(type)){
            // 登录成功
            LOGGER.info("登录成功");
        } else if ("D0003".equals(type)){
            // 收到消息
            LOGGER.info("收到消息");
        } else if ("D0004".equals(type)){
            // 转账事件
            LOGGER.info("转账事件");
        } else if ("D0005".equals(type)){
            // 撤回事件
            LOGGER.info("撤回事件");
        } else if ("D0006".equals(type)){
            // 好友请求
            LOGGER.info("好友请求");
        }
        return CommonResult.success("成功！");
    }
}
