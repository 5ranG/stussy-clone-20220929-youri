package com.stussy.stussyclone20220929youri.aop;

import com.stussy.stussyclone20220929youri.dto.CMRespDto;
import com.stussy.stussyclone20220929youri.exception.CustomValidationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class ValidationAop {
    @Pointcut("@annotation(com.stussy.stussyclone20220929youri.aop.annotation.ValidAspect)")
    private void pointCut(){}

//    @Around("pointCut()")
//    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) throws Throwable{ //리턴할게 없어서 void
        Object[] args = joinPoint.getArgs();
        // 매개변수 하나하나를 의미한다.

        BeanPropertyBindingResult bindingResult = null;

        for(Object arg : args){
            if(arg.getClass() == BeanPropertyBindingResult.class){
                //.getClass = 현재 참조하고 있는 클래스를 확인할 수 있는 메소드
                bindingResult = (BeanPropertyBindingResult) arg;
                break;
            }
        }

//        if(bindingResult == null){
//            return joinPoint.proceed(); // Around에만 사용되므로 Before때는 삭제.
//        }

        if (bindingResult.hasErrors()) { //bindingResult가 에러를 가지고 있으면 아래를 실행
            Map<String, String> errorMap = new HashMap<String, String>();

            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });

            throw new CustomValidationException("Validation failed:", errorMap);
//            return ResponseEntity.badRequest().body(new CMRespDto<>(-1, "유효성 검사 실패",errorMap));
        }
//        return joinPoint.proceed();
    }

    @AfterReturning(value = "pointCut()", returning = "returnObj") //before 쓸 때 필요. 메소드로 돌아가게해줌
    public void afterReturning(JoinPoint joinPoint, Object returnObj){
        log.info("Validation success: {}", returnObj);
    }


}



