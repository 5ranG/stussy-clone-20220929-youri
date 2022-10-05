package com.stussy.stussyclone20220929youri.controller.api;

import com.stussy.stussyclone20220929youri.aop.annotation.LogAspect;
import com.stussy.stussyclone20220929youri.aop.annotation.ValidAspect;
import com.stussy.stussyclone20220929youri.dto.CMRespDto;
import com.stussy.stussyclone20220929youri.dto.account.RegisterReqDto;
import com.stussy.stussyclone20220929youri.dto.validation.ValidationSequence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/api/account")
@RestController
public class AccountApi {

    @LogAspect
    @ValidAspect
    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated(ValidationSequence.class) @RequestBody RegisterReqDto registerReqDto, BindingResult bindingResult) { //json으로 받아야하니 리퀘스트바디

            return ResponseEntity.ok(null);
        }
    }
