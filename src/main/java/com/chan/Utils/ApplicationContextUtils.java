package com.chan.Utils;


import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public interface ApplicationContextUtils extends ApplicationContextAware {
    @Override
    void setApplicationContext(ApplicationContext varl);
}
