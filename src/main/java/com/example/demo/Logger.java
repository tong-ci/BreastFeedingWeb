package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.demo.entity.ReturnMsg;

@Aspect
@Component
public class Logger {
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
//	@Before("execution(* com.example.demo.controller.*.*(..))")
//	public void before(JoinPoint joinPoint) {
//		String mName = joinPoint.getSignature().getName();
//		List<Object> objects = Arrays.asList(joinPoint.getArgs());
//		System.out.println(formatter.format(new Date())+" Access Interface log "+mName+objects.subList(0, objects.size()-1));
//	}
//	
//	@AfterReturning(value = "execution(* com.example.demo.controller.*.*(..))",returning = "result")
//	public void afterReturning(JoinPoint joinPoint , Object result) {
//		String mName = joinPoint.getSignature().getName();
//		System.out.println(formatter.format(new Date())+" "+mName + " return msg "+result);
//	}
//	
//	@AfterThrowing(value = "execution(* com.example.demo.controller.*.*(..))",throwing =  "e")
//	public void afterThrowing(JoinPoint joinPoint , Exception e ) {
//		String mName = joinPoint.getSignature().getName();
//		System.out.println(formatter.format(new Date())+" "+mName + " error msg "+e);
//	}
	
	@Around("execution(* com.example.demo.controller.*.*(..))")
	public Object around(ProceedingJoinPoint point) {
		String mName = point.getSignature().getName();
		List<Object> objects = Arrays.asList(point.getArgs());
		System.out.println("\n"+formatter.format(new Date()) +"Access Interface "+mName+" log:");
		try {
			System.out.println(mName+" parameter "+objects.subList(0, objects.size()-1));
			Object object = point.proceed();
			System.out.println(mName+" return msg "+object);
			return object;
		}catch (Throwable e) {
			System.out.println(mName+" error msg "+e);
		}
		
		return new ReturnMsg(500, "server error").toString();
	}


}
