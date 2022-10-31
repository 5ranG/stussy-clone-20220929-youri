package com.stussy.stussyclone20220929youri.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig { //CORS
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 자격증명 허용 설정
        config.setAllowCredentials(true); // 오리진에서 모든 요청 다 허용
        config.addAllowedHeader("*"); // 모든 헤더내용 다 허용
        config.addAllowedMethod("*"); // 모든 메소드 요청 다 허용
        source.registerCorsConfiguration("/**", config); // 모든 요청에 위 설정을 적용
        return new CorsFilter(source);
    }
}
