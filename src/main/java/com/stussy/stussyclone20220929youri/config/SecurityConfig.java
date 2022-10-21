package com.stussy.stussyclone20220929youri.config;

import com.stussy.stussyclone20220929youri.handler.auth.AuthFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 기존의 WebSecurityConfigurerAdapter 클래스를 해당 SecurityConfig로 대체하겠다.
// 시큐리티에 등록하는거. extends랑 별개
@Configuration //스프링의 설정 클래스. 이래야 IoC에 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean // @Configuration와 세트로 사용. IoC빈등록방법
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 이게 기존세팅: super.configure(http);
        // security password: 71fa6981-7015-4029-a171-e6cbfc40aef4 이걸 만들어준다
        //logout도 자동으로 만들어짐. 관련 페이지 전부 막힘... (localhost:8000 전부 막힘)
        
        //아래는 override 세팅법
        http.csrf().disable();
        http.httpBasic().disable(); // httpBasic에서 제공해주는 로그인 사용X 선언. 비활성화.
        http.authorizeRequests() // 모든 요청시에 실행을 해라

                /*<<<<<<<<< Page >>>>>>>>>*/
                .antMatchers("/admin/**", "/api/admin/**")
//                .hasRole(("ADMIN")) // ADMIN 권한이 필요하다. 하나의 권한
                .access("hasRole('ADMIN') or hasRole('MANAGER')")
                .antMatchers("/account") // [메인객체] 해당 요청 주소들은
                .access("hasRole('USER') or hasRole('ADMIN') or hasRole('MANAGER')")
                //.authenticated() // 인증이 필요하다.

                .antMatchers("/", "/index", "/collections/")
                .permitAll()
                .antMatchers("/account/login", "/account/register")
                .permitAll()

                /*<<<<<<<<< Resource >>>>>>>>>*/
                .antMatchers("/static/**", "/image/**")
                .permitAll() // 모두 접근 권한 허용해라

                /*<<<<<<<<< API >>>>>>>>>*/
                .antMatchers("/api/account/register")
                .permitAll()

                .anyRequest() // antMatchers외의 다른 모든 요청들은
                .permitAll() // 수업을 위해 잠시 허용시켜둠
                //.denyAll() // 모두 접근 권한 차단해라

                .and() // 그리고!!!
                .formLogin() // [메인객체] 폼 로그인 방식으로 인증해라
                    .usernameParameter("email") //username일 경우 생략가능 (기본값이 username)
                    //PrincipalDetailService 에서 loadUserByUsername의 (파라미터)와 값이 동일해야함!
                    .loginPage("/account/login") // 우리가 만든 로그인 페이지를 사용해라 (GET 요청)
                    .loginProcessingUrl("/account/login")
                    // 로그인 로직(PrincipalDetailsService)의 loadUserByUsername 메소드 호출 (post 요청)
                    // security 라이브러리에서 컨트롤러가 이미 만들어져있다. 거기에 포스트매핑한다.
                    // html form 에서 /account/login 로 요청을 날림!
//                    .failureHandler()
                    .failureHandler(new AuthFailureHandler())
                    .defaultSuccessUrl("/index"); // "이전 페이지가 없는 경우"에만 로그인 성공시 index로 보낸다.
                    //보통 자동으로 이전 페이지를 기억했다가 로그인 페이지 전 페이지로 돌아간다.
    }
}
