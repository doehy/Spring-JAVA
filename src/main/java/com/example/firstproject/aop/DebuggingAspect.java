package com.example.firstproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect // AOP 클래스 선언 : 부가 기능을 주입하는 클래스
@Component // Ioc 컨테이너가 해당 객체를 생성 및 관리
@Slf4j // 로깅 기능을 위해 추가함
public class DebuggingAspect {

    // 대상 메소드를 선택하는 어노테이션: api 패키지의 모든 메소드
    @Pointcut("execution(* com.example.firstproject.api.*.*(..))")
    private void cut() {}

    // 실행 시점 설정을 해주는 어노테이션이다. 즉, 여기서는 cut()의 대상이 수행되기 이전에 수행된다.
    @Before("cut()")
    public void loggingArgs(JoinPoint joinPoint) { //joinpoint가 의미하는 것이 cut의 메소드를 둘러싼 결합지점
        // 입력값 가져오기
        Object[] args = joinPoint.getArgs();

        // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();
        // 메소드명
        String methodName = joinPoint.getSignature()
                .getName();

        // 입력값 로깅하기
        // CommentService#create()의 입력값 => 5
        // CommentService#create()의 입력값 => CommentDto(id=null, ...)
        for (Object obj : args) { // foreach문
            log.info("{}#{}의 입력값 =? {}", className,methodName,obj);
        }
    }

    // 실행 시점 설정 : cut()에 지정된 대상 호출 성공 후!
    @AfterReturning(value = "cut()",returning = "returnobj")
    public void loggingReturnValue(JoinPoint joinPoint, // cut()의 대상 메소드 (정확하게는 아니지만 일단 대략적인 의미)
                                   Object returnobj) { // 리턴값

        // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();
        // 메소드명
        String methodName = joinPoint.getSignature()
                .getName();

        // 반환값 로깅
        // CommentService#create()의 반환값 => CommentDto(id=10, ....)
        log.info("{}#{}의 반환값 =? {}", className,methodName,returnobj);

    }
}
