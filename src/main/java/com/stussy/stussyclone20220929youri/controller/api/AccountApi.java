package com.stussy.stussyclone20220929youri.controller.api;

import com.stussy.stussyclone20220929youri.dto.RegisterReqDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/account")
@RestController
public class AccountApi {

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReqDto registerReqDto){ //json으로 받아야하니 리퀘스트바디
        log.info("{}", registerReqDto);
        return ResponseEntity.ok(null);
    }
}
