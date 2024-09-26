package com.cellosquare.adminapp.common.aop;

import cn.hutool.extra.spring.SpringUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.cellosquare.adminapp.common.util.AwsUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Aspect
public class CleanCacheAop {
    final String activeProfile = XmlPropertyManager.getPropertyValue("aws.profiles");
    @Pointcut("@annotation(com.cellosquare.adminapp.common.adminAnnotion.CleanCacheAnnotion)")
    private void cleanCacheAopAspect(){

    }

    @After("cleanCacheAopAspect()")
    public void doAfter(JoinPoint point){
        if (Objects.equals(activeProfile,"prod")) {
            AwsUtil.CleanCdnCache();
        }
    }
}
