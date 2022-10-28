package com.stussy.stussyclone20220929youri.dto.order;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class OrderReqDto {
    private int groupId;
    private String productName;
    private int productPrice;
    private String productSize;
    private String productColor;
    private String productImg;

}
