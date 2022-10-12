package com.stussy.stussyclone20220929youri.exception;


public class CustomInternalServerErrorException extends RuntimeException{
    // 500에러가 떴을때 날려줄 파일

    public CustomInternalServerErrorException(String message){
        super(message);
    }

}
