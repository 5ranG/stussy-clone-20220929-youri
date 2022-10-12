package com.stussy.stussyclone20220929youri.service.admin;

import com.stussy.stussyclone20220929youri.dto.admin.ProductAdditionReqDto;

public interface ProductService {
    public boolean addProduct(ProductAdditionReqDto productAdditionReqDto) throws Exception;
}
