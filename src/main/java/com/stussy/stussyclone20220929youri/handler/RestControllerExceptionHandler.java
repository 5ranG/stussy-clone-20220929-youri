package com.stussy.stussyclone20220929youri.handler;

import com.stussy.stussyclone20220929youri.dto.CMRespDto;
import com.stussy.stussyclone20220929youri.exception.CustomInternalServerErrorException;
import com.stussy.stussyclone20220929youri.exception.CustomValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController // 예외를 낚아챔 (본인과 일치하는 예외) @ControllerAdvice + @ResponseBody
@RestControllerAdvice // 예외를 낚아채게 도와줌
public class RestControllerExceptionHandler {
    @ExceptionHandler(CustomValidationException.class) //CustomValidationException 예외가 발생하면 실행
    //@Controller, @RestController가 적용된 Bean내에서 발생
    public ResponseEntity<?> validationErrorException(CustomValidationException e){
        return ResponseEntity
                .badRequest()
                .body(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()));
    }

    @ExceptionHandler(CustomInternalServerErrorException.class)
    public ResponseEntity<?> internalServerErrorException(CustomInternalServerErrorException e){
        return ResponseEntity
                .internalServerError()
                .body(new CMRespDto<>(-1, e.getMessage(), null));
    }
}
