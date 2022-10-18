package com.stussy.stussyclone20220929youri.controller.api;

import com.stussy.stussyclone20220929youri.aop.annotation.LogAspect;
import com.stussy.stussyclone20220929youri.aop.annotation.TimeAspect;
import com.stussy.stussyclone20220929youri.aop.annotation.ValidAspect;
import com.stussy.stussyclone20220929youri.dto.CMRespDto;
import com.stussy.stussyclone20220929youri.dto.account.RegisterReqDto;
import com.stussy.stussyclone20220929youri.dto.validation.ValidationSequence;
import com.stussy.stussyclone20220929youri.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/account")
@RestController
@RequiredArgsConstructor // accountService 관련
public class AccountApi {
    private final AccountService accountService;

    @LogAspect // annotation 만든것
    @ValidAspect
//    @TimeAspect
    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated(ValidationSequence.class) @RequestBody RegisterReqDto registerReqDto,
           // @Validated(ValidationSequence.class): RegisterDto 에서 message 순서 지정 유효성검사
           // @RequestBody: json으로 받아야하니까.
                                      BindingResult bindingResult) throws Exception {
           // BindingResult: 유효성 검증. @Validated와 세트
        accountService.checkDuplicateEmail(registerReqDto.getEmail());
            accountService.register(registerReqDto);

            return ResponseEntity.ok().body(new CMRespDto<>(1, "Successfully registered", registerReqDto));
        }
    }
