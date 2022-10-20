package com.stussy.stussyclone20220929youri.controller.admin.api;

import com.stussy.stussyclone20220929youri.aop.annotation.LogAspect;
import com.stussy.stussyclone20220929youri.aop.annotation.ValidAspect;
import com.stussy.stussyclone20220929youri.dto.CMRespDto;
import com.stussy.stussyclone20220929youri.dto.admin.ProductAdditionReqDto;
import com.stussy.stussyclone20220929youri.dto.admin.ProductModificationReqDto;
import com.stussy.stussyclone20220929youri.dto.validation.ValidationSequence;
import com.stussy.stussyclone20220929youri.service.admin.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/admin")
@RestController
@RequiredArgsConstructor
public class ProductApi {

    private final ProductService productService;;

    @ValidAspect
    @LogAspect
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@Validated(ValidationSequence.class) ProductAdditionReqDto productAdditionReqDto, BindingResult bindingResult) throws Exception {

        String productName = productAdditionReqDto.getName();

        for(int i = 0; i < 20; i++){
            if(i % 4 == 0){
                productAdditionReqDto.setName(productName + "-" + (i + 1));
            }
            productService.addProduct(productAdditionReqDto);
        }
        return ResponseEntity
                .created(null)
                .body(new CMRespDto<>(1, "Successfully", null));

//        return ResponseEntity //https://www.inflearn.com/questions/176104
//                .created(null) //  created() 메소드는 반환 객체에 대한 response 타입을 결정
//                .body(new CMRespDto<>(1, "Successfully", productService.addProduct(productAdditionReqDto)));
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProductList(@RequestParam int page,
                                            @RequestParam @Nullable String category,
                                            @RequestParam @Nullable String searchValue) throws Exception {

        return ResponseEntity.ok(new CMRespDto<>(1, "Successfully", productService.getProductList(page, category, searchValue)));
    }

    @LogAspect
//    @ValidAspect
    @PostMapping("/product/modification")
    public ResponseEntity<?> updateProduct(ProductModificationReqDto productModificationReqDto) throws Exception {

        return ResponseEntity.ok(new CMRespDto<>(1, "Successfully", true));
    }
}