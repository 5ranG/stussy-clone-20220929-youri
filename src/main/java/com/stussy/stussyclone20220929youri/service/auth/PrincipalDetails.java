package com.stussy.stussyclone20220929youri.service.auth;

import com.stussy.stussyclone20220929youri.domain.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class PrincipalDetails implements UserDetails {

    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //계정이 가지고 있는 권한 목록 리턴
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        authorities.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return null;
//            }
//        });
        authorities.add(() -> user.getRole().getRole()); // 위를 람다로 바꾼식
        return authorities;
    }

    @Override
    public String getPassword() { // 계정의 비밀번호 리턴
        return user.getPassword();
    }

    @Override
    public String getUsername() { // 계정의 이름 리턴
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정의 만료여부(계정의 사용기간 만료, true: 만료X)
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정의 잠김 여부(관리자가 강제로 사용을 금한 경우, true: 잠김X)
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 비밀번호 만료 여부(비밀번호 5회 이상 틀렸을 때)
    }

    @Override
    public boolean isEnabled() {
        return true; // 계정의 활성화 여부(휴먼계정, 이메일 또는 전화번호 인증필요)
    }
}
