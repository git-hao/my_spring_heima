package com.hao.service;

import com.hao.dao.UserDao;
import com.hao.dao.UserDaoImpl;
import com.hao.factory.BeanFactory;

/**
 * @Describe com.hao.service
 * @Auther wenhao chen
 * @CreateDate 2019/7/28
 * @Version 1.0
 *
 */
public class UserServiceImpl implements UserService{

    //private UserDao ud = new UserDaoImpl();
    //使用beanFactory
    private UserDao ud = (UserDao) BeanFactory.getBean("userDao");

    //定义类，被方法调用，验证单例,所以，方法调用的变量，最好放在方法内
    private int i =0;

    public void saveUser() {
        i++;
        int j = 0;
        j++;
        ud.saveUser();
        System.out.println("i="+i+"..."+"j="+j);
    }
}
