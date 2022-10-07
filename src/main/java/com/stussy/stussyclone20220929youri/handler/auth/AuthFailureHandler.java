package com.stussy.stussyclone20220929youri.handler.auth;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 로그인에서 아이디나 비밀번호가 잘못되었습니다.
        if(exception.getClass() == UsernameNotFoundException.class || exception.getClass() == BadCredentialsException.class){
            response.sendRedirect("/account/login?error=auth");
        }else{
            response.sendRedirect("/account/login?error");
        }
    }
}
