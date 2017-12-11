package com.kaishengit.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kaishengit.service.UserService;

@Service(timeout = 5000)
public class UserServiceImpl implements UserService {
    @Override
    public void sayHello(String name) {
        System.out.println(">>>>>>>>>>>>>> Hello," + name);
    }
}
