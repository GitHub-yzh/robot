package com.robot.yzh;

import com.robot.yzh.service.auth.IAuthenticationService;
import com.robot.yzh.service.gpt.IGptService;
import com.robot.yzh.service.message.IMessageService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RobotApplication {

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    IMessageService messageService;

    @PostConstruct
    public void init(){
        String verify = authenticationService.verify("1234567890");
        messageService.NewMessgae(verify);

//        gptService.gpt4("你是谁");
    }

    public static void main(String[] args) {
        SpringApplication.run(RobotApplication.class, args);
    }

}
