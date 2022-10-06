package com.stussy.stussyclone20220929youri.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAop {
    @Pointcut("@annotation(com.stussy.stussyclone20220929youri.aop.annotation.LogAspect)") //해당 어노테이션 위치
    private void annotationPointCut(){}

//    @Pointcut("execution(* com.stussy.stussyclone20220929youri.controller.api.*.*(..)")
//    private void executionPointCut() {}

    @Around("annotationPointCut()") // 둘다 쓰고 싶을 때는 ("annotationPointCut() || executionPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs(); // 매개변수 하나하나를 의미한다.

        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        //메소드정보를 다 담고있는 getSignature를 다운캐스팅해주면
        //매개변수의 변수명들을 다 가져올 수 있다.

        String[] argNames = codeSignature.getParameterNames();
        //이게 가져온 변수명들. 파라미터들의 이름들.

        StringBuilder argNameString = new StringBuilder();
        StringBuilder argDataString = new StringBuilder();

        for(int i = 0; i < args.length; i++) {
            argNameString.append(argNames[i]);
            argDataString.append(args[i].toString());
            if(i < args.length - 1) {
                argNameString.append(", ");
                argDataString.append(", ");
            }
        }
        log.info("Method Call -- {}.{}({}) >> {}", //클래스명.매소드명(매개변수)
                joinPoint.getSignature().getDeclaringTypeName(), // 클래스명
                joinPoint.getSignature().getName(), // 메소드명
                argNameString.toString(), // 매개변수명(파라미터명)
                argDataString.toString()); // 그 내용 데이터

        Object result = joinPoint.proceed(); // 실행 시점. *(..))를 호출하는 시점?

        log.info("Method Return -- {}.{}({}) >> {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                argNameString.toString(), // 리턴이 일어났고
                result); // 그 결과이다. <200 ko ko, []>

        return result;
    }
}