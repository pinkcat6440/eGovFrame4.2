package egovframework.lab.aop.aspectjannotation;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import egovframework.lab.aop.common.BizException;

@Component("aspectUsingAnnotation")
@Aspect
public class AspectUsingAnnotation {

	private static final Logger LOGGER = LoggerFactory.getLogger(AspectUsingAnnotation.class);

	@Pointcut("execution(public * egovframework.lab.aop..Annotation*Impl.*(..))")
	public void targetMethod() {
		// 더미
	}

	@Before("targetMethod()")
	public void beforeTargetMethod(JoinPoint thisJoinPoint) {
		LOGGER.debug("\nAspectUsinAnnotation.beforeTargetMethod executed.");
		
		@SuppressWarnings("unused")
		Class<? extends Object> clazz = thisJoinPoint.getTarget().getClass();
		String className = thisJoinPoint.getTarget().getClass().getSimpleName();
		String methodName = thisJoinPoint.getSignature().getName();
		
		StringBuffer buf = new StringBuffer();
		buf.append("\n== AspectUsingAnnotation.beforeTargetMethod : [" + className + "." + methodName + "()] ==");
		Object[] arguments = thisJoinPoint.getArgs();
		int argCount = 0;
		for(Object obj : arguments) {
			buf.append("\n - arg");
			buf.append(argCount++);
			buf.append(" : ");
			buf.append(ToStringBuilder.reflectionToString(obj));
		}
		LOGGER.debug(buf.toString());
	}
	
	@After("targetMethod()")
	public void afterTargetMethod(JoinPoint thisJoinPoint) {
		LOGGER.debug("AspectUsingAnnotation.afterTargetMethod executed");
	}
	
	@AfterReturning(pointcut = "targetMethod()", returning = "retVal")
	public void afterReturningTargetMethod(JoinPoint thisJoinPoint, Object retVal) {
		LOGGER.debug("AspectUsingAnnotation.afterReturningTargetMethod executed. return value is [{}]", retVal);
		
		@SuppressWarnings("unused")
		Class<? extends Object> clazz = thisJoinPoint.getTarget().getClass();
		String className = thisJoinPoint.getTarget().getClass().getSimpleName();
		String methodName = thisJoinPoint.getSignature().getName();
		StringBuffer buf = new StringBuffer();
		buf.append("\n== AspectUsingAnnotation.afterReturningTargetMethod : [" + className + "." + methodName + "()] ==");
		buf.append("\n");
	
		if(retVal instanceof List) {
			List<?> resultList = (List<?>) retVal;
			buf.append("resultList size :" + resultList.size() + "\n");
			for(Object oneRow : resultList) {
				buf.append(ToStringBuilder.reflectionToString(oneRow));
				buf.append("\n");
			}
		}else {
			// buf.append(ToStringBuilder.reflectionToString(retVal));
		}
		LOGGER.debug(buf.toString());
	}


    @AfterThrowing(pointcut = "targetMethod()", throwing = "exception")
    public void afterThrowingTargetMethod(JoinPoint thisJoinPoint,
            Exception exception) throws Exception {
    	LOGGER.debug("AspectUsingAnnotation.afterThrowingTargetMethod executed.");
    	LOGGER.error("에러가 발생했습니다. {}", exception);

        // 원본 exception 을 wrapping 하고 user-friendly 한
        // 메시지를 설정하여 새로운 Exception 으로 re-throw
        throw new BizException("에러가 발생했습니다.", exception);

        // 여기서는 간단하게 작성하였지만 일반적으로 messageSource 를 사용한
        // locale 에 따른 다국어 처리 및 egov. exceptionHandler
        // 를 확장한 Biz. (ex. email 공지 등) 기능 적용이 가능함.
    }
	
	@Around("targetMethod()")
	public Object aroundTargetMethod(ProceedingJoinPoint thisJoinPoint) throws Throwable{
		LOGGER.debug("AspectUsingAnnotation.aroundTargetMethod start.");
		long time1= System.currentTimeMillis();
		Object retVal = thisJoinPoint.proceed();
		
		long time2 = System.currentTimeMillis();
		LOGGER.debug("AspectUsingAnnotation.aroundTargetMethod end. Time({})", (time2 - time1) );
		return retVal;
	}
	

}
