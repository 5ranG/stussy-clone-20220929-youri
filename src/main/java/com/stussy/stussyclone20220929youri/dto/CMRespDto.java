package com.stussy.stussyclone20220929youri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CMRespDto <T>{
    private int code;
    private String msg;
    private T data;
}
