package com.stussy.stussyclone20220929youri.dto.account;

import com.stussy.stussyclone20220929youri.domain.User;
import com.stussy.stussyclone20220929youri.dto.validation.ValidationGroups;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterReqDto {
    @NotBlank(message = "이름은 비워 둘 수 없습니다",
            groups = ValidationGroups.NotBlankGroup.class)
    @Size(min = 1, max = 3,
            message = "이름은 한글자에서 세글자 사이여야 합니다",
            groups = ValidationGroups.SizeCheckGroup.class)
    @Pattern(regexp = "^[가-힇]*$",
            message="이름은 한글만 입력 가능합니다",
            groups= ValidationGroups.PatternCheckGroup.class
    ) // 정규표현식 이름
    private String lastName;

    @NotBlank(message = "성은 비워 둘 수 없습니다",
            groups = ValidationGroups.NotBlankGroup.class)
    @Size(min = 1, max = 2,
            message = "성은 한글자에서 세글자 사이여야 합니다",
            groups = ValidationGroups.SizeCheckGroup.class)
    @Pattern(regexp = "^[가-힇]*$",
            message="성은 한글만 입력 가능합니다",
            groups= ValidationGroups.PatternCheckGroup.class)
    private String firstName;

    @Email(message = "잘못된 이메일 형식입니다")
    @NotBlank(message = "이메일은 비워 둘 수 없습니다",
            groups = ValidationGroups.NotBlankGroup.class) // 빈값일 수 없다.
    private String email;

    @NotBlank(message = "비밀번호는 비워 둘 수 없습니다",
            groups = ValidationGroups.NotBlankGroup.class)
    @Size(min = 8, max = 16,
            message = "비밀번호는 8자에서 16자여야 합니다",
            groups = ValidationGroups.SizeCheckGroup.class)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[~!@#$%^&*_])[a-zA-Z\\d-!@#$%^&*_]{8,16}$",
            message="숫자, 영문, 특수기호를 하나 이상 포함하여 작성해야 합니다",
            groups= ValidationGroups.PatternCheckGroup.class)
    // () or () or () 중에 포함되어야 하며 [] 만 사용가능하며 {} 글자수제한
    private String password;

    public User toUserEntity(){
        return User.builder()
                .username(email)
                .password(new BCryptPasswordEncoder().encode(password)) // 암호화 시키는 것
                .name(firstName + lastName)
                .email(email)
                .role_id(1)
                .build();
    }

}
