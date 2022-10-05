package com.stussy.stussyclone20220929youri.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/*
* 메소드 실행 시간을 계산해주는 로직
*/

@Slf4j
@Aspect
@Component // IOC에 등록. 개발자가 직접 컨트롤할 수 있는 클래스(직접 만든)를 Bean으로 등록하고 싶은 경우
public class TimerAop {
    @Pointcut("execution(* com.stussy.stussyclone20220929youri.controller..*.*(..))")
    private void executionPointCut(){} // 메소드명으로 처리하기 위해 만듬

        @Around("executionPointCut()")
        public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
            //joinPoint 는 실행되는 메소드의 정보
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            //↑전처리
            Object result = joinPoint.proceed(); // 실행 시점
            // ↓후처리
            stopWatch.stop();
            log.info("class: {}, method: {} >>> {}",
                    joinPoint.getSignature().getDeclaringTypeName(),    // 클래스명
                    joinPoint.getSignature().getName(),                 // 메소드명
                    stopWatch.getTotalTimeSeconds());

            return  result;
        }
}
