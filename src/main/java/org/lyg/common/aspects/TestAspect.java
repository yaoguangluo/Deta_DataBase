//package org.lyg.common.aspects;
//import lombok.extern.slf4j.Slf4j;
//
//
//import java.util.Enumeration;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.lyg.cache.Cache;
//import org.lyg.cache.CacheManager;
//import org.lyg.common.exceptions.RequestLimitException;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//@Aspect
//@Component
//@Slf4j
//public class TestAspect {
//	public static Log log = LogFactory.getLog(TestAspect.class);
//	public void pointCut() {
//		System.out.println(1);
//	}
//
//	@AfterReturning("execution(* org.lyg.*.*(..))")
//	public void doAfter(JoinPoint jp) {
//		System.out.println("log Ending method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
//	}
//
//	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
//		long time = System.currentTimeMillis();
//		Object retVal = pjp.proceed();
//		time = System.currentTimeMillis() - time;
//		System.out.println("process time: " + time + " ms");
//		return retVal;
//	}
//
//	@Before("execution(* org.lyg.*.*(..))")
//	public void doBefore(JoinPoint jp) {
//		System.out.println("log Begining method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
//	}
//
//	public void doThrowing(JoinPoint jp, Throwable ex) {
//		System.out.println("method " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + " throw exception");
//		System.out.println(ex.getMessage());
//	}
//
//	@SuppressWarnings("rawtypes")
//	@Before("execution(* org.lyg.vpc.controller.port.*.*(..))")
//	public void requestLimit(final JoinPoint joinPoint) throws RequestLimitException {	
//		try {
//			String ip = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
//					.getRequest().getRemoteAddr();	
//			HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
//					.getRequest();
//			Enumeration enumeration = httpServletRequest.getHeaderNames();
//			String ipAgen = "";
//			while(enumeration.hasMoreElements()) {
//				 String headerName = enumeration.nextElement().toString();
//				 Enumeration<String> enumerationOfHeaders = httpServletRequest.getHeaders(headerName);
//				 while(enumerationOfHeaders.hasMoreElements()) {
//					 String temp = enumerationOfHeaders.nextElement();
//					 if(headerName.contains("Cookie")&&temp.contains("ip="))
//						 ipAgen += temp;
//				 }
//			}
//			String url = joinPoint.getTarget().getClass().getName();
//			String key = "req_limit_".concat(url).concat(ip);
//			log.info("request Info->" + url + "--" + ipAgen);
//			//没有或者过期了就重置
//			Cache temp=CacheManager.getCacheInfo(key);
//			if (temp == null) {
//				Cache cache = new Cache();
//				cache.setValue("1");
//				cache.setExpired(false);
//				cache.setTimeOut(2000+System.currentTimeMillis());
//				CacheManager.putCache(key, cache);
//			} else if(temp.isExpired()) {
//				Cache cache = new Cache();
//				cache.setValue("1");
//				cache.setExpired(false);
//				cache.setTimeOut(2000+System.currentTimeMillis());
//				CacheManager.putCache(key, cache);
//			}else if (!temp.isExpired()//没有过期小于限制就增加
//					&& Integer.valueOf(temp.getValue().toString()) <= 6) {
//				int digit = Integer.valueOf(CacheManager.getCacheInfo(key).getValue().toString()) + 1;
//				CacheManager.getCacheInfo(key).setValue(""+digit);
//			} else if (!temp.isExpired()//没过期大于限制
//					&& Integer.valueOf(temp.getValue().toString()) > 6) {
//				throw new RequestLimitException();
//			}
//		} catch (RequestLimitException e) {
//			throw e;
//		} catch (Exception e) {
//			log.error("error request: ", e);
//		}
//	}
//}