package de.allianz.springboot.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Log
@Component
public class TimeLogger {
    private static final Logger LOG =  LoggerFactory.getLogger(TimeLogger.class);
    private static final String LOGGING_MESSAGE = "Method {} in class {} took {} ms to execut!";
    @Around("bean(toDo*)")
    public Object logControllerExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Long start = System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed();
        Long end = System.currentTimeMillis();

        Long diffrence = end - start;

        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().getName();

        LOG.info(LOGGING_MESSAGE,methodName,className,diffrence);
        return proceed;


    }
}
