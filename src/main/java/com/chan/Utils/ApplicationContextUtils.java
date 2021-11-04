package com.chan.Utils;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext varl) throws BeansException{
        context = varl;
    }

    //根据bean名字获取工厂中指定对象
    public static Object getbean(String beanName){
        return context.getBean(beanName);
    }
}
