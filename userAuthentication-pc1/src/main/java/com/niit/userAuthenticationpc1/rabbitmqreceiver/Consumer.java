package com.niit.userAuthenticationpc1.rabbitmqreceiver;

import com.niit.userAuthenticationpc1.domain.Users;
import com.niit.userAuthenticationpc1.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private UserService userService;

    @RabbitListener(queues = "user_queue")
    public void getDtoFromQueueAndAddToDb(UserDTO userDTO) {
        Users users=new Users();
        users.setEmailId(userDTO.getEmailId());
        users.setPassword(userDTO.getPassword());

        Users users1=userService.saveUser(users);
        System.out.println("result = "+users1);
    }

}
