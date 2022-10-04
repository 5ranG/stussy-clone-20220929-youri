package com.stussy.stussyclone20220929youri.controller.api;

import com.stussy.stussyclone20220929youri.dto.CMRespDto;
import com.stussy.stussyclone20220929youri.dto.account.RegisterReqDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/api/account")
@RestController
public class AccountApi {

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterReqDto registerReqDto, BindingResult bindingResult) { //json으로 받아야하니 리퀘스트바디

        if (bindingResult.hasErrors()) {
            log.error("유효성 검사 요류가 발생");
            Map<String, String> errorMap = new HashMap<String, String>();

            List<List<FieldError>> codeList = new ArrayList<List<FieldError>>();
            codeList.add(new ArrayList<FieldError>()); // Pattern
            codeList.add(new ArrayList<FieldError>()); // NotBlank

            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
                if (error.getCode().equals("Pattern")) {
                    codeList.get(0).add(error);
                } else if (error.getCode().equals("NotBlank")) {
                    codeList.get(1).add(error);
                }
            });

            log.info("codeList: {}", codeList);

            codeList.forEach(errorMapList -> {
                errorMapList.forEach(error -> {
                    errorMap.put(error.getField(), error.getDefaultMessage());
                });
                log.info("error: {}", errorMap);
            });
/*
            bindingResult.getFieldErrors().forEach(error -> {
                log.info("Error: 필드명 ({}), 메세지({})", error.getField(), error.getDefaultMessage());
                if(!error.getCode().equals("NotBlank")){
                    errorMap.put(error.getField(), error.getDefaultMessage());
                }
            });
            bindingResult.getFieldErrors().forEach(error -> {
                log.info("Error: 필드명 ({}), 메세지({})", error.getField(), error.getDefaultMessage());
                if(error.getCode().equals("NotBlank")){
                    errorMap.put(error.getField(), error.getDefaultMessage());
                }
            });
*/
                return ResponseEntity.badRequest().body(new CMRespDto<>(-1, "유효성 검사 실패", errorMap));
            }
            log.info("{}", registerReqDto);
            return ResponseEntity.ok(null);
        }
    }
