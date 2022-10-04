package com.stussy.stussyclone20220929youri.dto.account;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class RegisterReqDto {
    @Pattern(regexp = "^[가-힇]{1,3}$", message="이름은 한글만 입력가능하며 한글자 이상 세글자 이하로 작성하세요") // 정규표현식 이름
    private String lastName;
    @Pattern(regexp = "^[가-힇]{1,3}$", message="성은 한글만 입력가능하며 한글자 이상 두글자 이하로 작성하세요")
    private String firstName;

    @Email(message = "잘못된 이메일 형식입니다.")
    @NotBlank(message = "이메일은 비워 둘 수 없습니다.") // 빈값일 수 없다.
    private String email;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[~!@#$%^&*_])[a-zA-Z\\d-!@#$%^&*_]{8,16}$", message="숫자, 영문(대소문자), 특수기호를 하나 이상 포함하여야 하며 8자 이상 16자 이하로 작성해야 합니다.")
    @NotBlank(message = "비밀번호는 비워 둘 수 없습니다.")
    // () or () or () 중에 포함되어야 하며 [] 만 사용가능하며 {} 글자수제한
    private String password;
}
