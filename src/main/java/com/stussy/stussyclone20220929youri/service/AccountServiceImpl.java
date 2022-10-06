package com.stussy.stussyclone20220929youri.service;

import com.stussy.stussyclone20220929youri.domain.User;
import com.stussy.stussyclone20220929youri.dto.account.RegisterReqDto;
import com.stussy.stussyclone20220929youri.exception.CustomValidationException;
import com.stussy.stussyclone20220929youri.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;

    @Override
    public boolean checkDuplicateEmail(String email){
        User user = accountRepository.findUserByEmail(email); // select 된다.
        if (user != null) { // 이메일이 기존에 이미 있다면, (중복)
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("duplicateFlag", "이미 가입된 이메일입니다");
            throw new CustomValidationException("DuplicateEmail Email", errorMap);
        }
        return true;
    }

    @Override
    public boolean register(RegisterReqDto registerReqDto) throws Exception {
//        User userEntity = registerReqDto.toUserEntity();
//        int result = accountRepository.save(userEntity);
//        return result != 0; 아래 한 줄로 요약 가능

        return accountRepository.save(registerReqDto.toUserEntity()) != 0;
    }

}
