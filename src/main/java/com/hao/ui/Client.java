package com.hao.ui;

import com.hao.factory.BeanFactory;
import com.hao.service.UserService;
import com.hao.service.UserServiceImpl;

/**
 * @Describe com.hao.ui
 * @Auther wenhao chen
 * @CreateDate 2019/7/28
 * @Version 1.0
 * 模拟表现层，调用业务层
 */
public class Client {
    public static void main(String[] args){
        //UserService us = new UserServiceImpl();
        //使用beanFactory
        for (int i = 0; i < 3; i++) {
            UserService us = (UserService) BeanFactory.getBean("userService");
            System.out.println(us);
            us.saveUser();
        }

    }
}
