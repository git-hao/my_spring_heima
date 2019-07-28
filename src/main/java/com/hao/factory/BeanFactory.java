package com.hao.factory;

import javax.management.ObjectName;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Describe com.hao.factory
 * @Auther wenhao chen
 * @CreateDate 2019/7/28
 * @Version 1.0
 *
 * bean:计算机英语中：可重用组件
 * javaBean：用java写的可重用组件
 *     范围： javaBean >> 实体类
 *     它就是创建service和dao对象的
 *     第一步：需要一个配置文件来配置service和dao
 *              配置内容：唯一标识：全限定类名（key：value）
 *     第二步：通过读取配置文件，反射创建对象
 *              配置文件：.xml   .properties
 * IOC,控制反转：
 *      把创建对象的权力交给框架，是框架的重要特征，并非面向对象编程的专用术语，包括依赖注入（Dependency Injection）
 *      和依赖查找（Dependency Lookup）
 *      削减耦合
 */
public class BeanFactory {


    //改造为单例
    //定义个properties对象
    private static Properties prop;
    //定义一个map，存放要创建的对象，称之为容器
    private static Map<String,Object> beans;
    //使用静态代码块为Properties对象赋值
    static {
        try{
            //实例化properties对象
            prop = new Properties();
            //获取properties的流对象,通过类加载器，从类的根路径加载resources下的文件
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            prop.load(in);
            //实例化容器
            beans = new HashMap<String, Object>();
            //取出所有的配置文件中的key
            Enumeration keys = prop.keys();
            //遍历枚举
            while (keys.hasMoreElements()){
                //取出key
                String key = keys.nextElement().toString();
                //根据key得到value
                String beanPath = prop.getProperty(key);
                //反射创建对象
                Object value = Class.forName(beanPath).newInstance();
                //key和value放入容器
                beans.put(key,value);
            }
        }catch (Exception e){
            throw new ExceptionInInitializerError("初始化properties文件错误");
        }
    }

    /**
     * 根据bean的name获取对象，单例
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName){
        return beans.get(beanName);
    }


    /**
     * 初版多例对象

    //定义个properties对象
    private static Properties prop;
    //使用静态代码块为Properties对象赋值
    static {
        try{
            //实例化properties对象
            prop = new Properties();
            //获取properties的流对象,通过类加载器，从类的根路径加载resources下的文件
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            prop.load(in);
        }catch (Exception e){
            throw new ExceptionInInitializerError("初始化properties文件错误");
        }
    }

    /**
     * 根据bean的name返回对应的bean对象
     * @param beanName
     * @return

    public static Object getBean(String beanName){
        Object bean = null;
        try{
            String beanPath = prop.getProperty(beanName);
            bean = Class.forName(beanPath).newInstance();
            //System.out.println(beanPath);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bean;
    }
     */
}
