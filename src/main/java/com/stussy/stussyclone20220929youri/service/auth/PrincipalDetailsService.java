package com.stussy.stussyclone20220929youri.service.auth;

import com.stussy.stussyclone20220929youri.domain.User;
import com.stussy.stussyclone20220929youri.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { //email 검사
        log.info("email >> {}", email);
        User user = accountRepository.findUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("존재하지 않는 아이디입니다");
        }
        return new PrincipalDetails(user); //UserDetails를 리턴해야하는데, 인페라서 파일을 새로 만들어야함
    }
}
