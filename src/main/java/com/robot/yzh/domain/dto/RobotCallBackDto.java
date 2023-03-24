package com.robot.yzh.domain.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * @author 杨峥辉
 * @version v1.0.0
 * @Package : com.robot.yzh.domain.dto
 * @Description : 回调入参对象
 * @Create on : 2023/3/24 14:47
 **/
@Data
public class RobotCallBackDto implements Serializable {

    /**
     * 事件的type（可用来区分是什么事件）
     */
    private String type;

    /**
     * 事件的描述问题
     */
    private String des;

    /**
     * 事件的主要内容，不同事件，具体对象参数也不尽相同
     */
    private Object data;

    /**
     * 13位现行时间戳
     */
    private String timestamp;

    /**
     * 这条数据来源微信 登录的wxid
     */
    private String wxid;

    /**
     * 这条数据来源微信 监听的端口
     */
    private Integer port;

    /**
     * 这条数据来源微信 的进程PID
     */
    private Integer pid;

    /**
     * 这条数据来源微信 启动时在进程参数中填写的自定义标识
     */
    private String flag;

}
