package com.erp.pma.logging;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLoggerAspect {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("within(com.erp.pma.conntoller..*)")
	private void displayLog(){
		
	}
	
	
	@Around("displayLog()")
	private Object log(ProceedingJoinPoint jp){
		log.debug("Before");
		log.debug("-------------------------- {}.{} () with arguments[s]={}",jp.getSignature().getDeclaringTypeName(),jp.getSignature().getDeclaringType().getName(),
				Arrays.toString(jp.getArgs()));
		Object o=null;
		try {
			o=jp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("After");
		log.debug("-------------------------- {}.{} () with arguments[s]={}",jp.getSignature().getDeclaringTypeName(),jp.getSignature().getDeclaringType().getName(),
				Arrays.toString(jp.getArgs()));
		
		return o;
	}

}
