package com.assignment.demo.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApiLoggerAspect {

    private static Logger logger;

    @Around("serviceMethods()")
    public Object logApiInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
        logger.info("Service Started.");
        logger.info(joinPoint.getSignature().getName());
        Object[] params = joinPoint.getArgs();
        if (params.length > 0)
            logger.info("inputs");

        for (Object obj : params) {
            logger.info(obj.toString());
        }
        Object result = joinPoint.proceed();
        logger.info("Service ended.");
        return result;
    }

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void serviceMethods() {
    }


}
