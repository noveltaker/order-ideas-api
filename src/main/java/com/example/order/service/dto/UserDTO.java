package com.example.order.service.dto;

import com.example.order.domain.User;
import com.example.order.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UserDTO {

    @Email(
            message = "이메일 형식이 아닙니다.",
            regexp = "^(.+)@(.+)$"
    )
    private String email;

    @Size(
            min = 10,
            message = "최소 10자 이상 입력하세요"
    )
    private String password;

    @Pattern(
            message = "한글, 영문 대소문자만 가능합니다.",
            regexp = "[A-Z|a-z|ㄱ-ㅎ|가-힣]"
    )
    private String name;

    @Pattern(
            message = "영문소문자만 가능합니다.",
            regexp = "[a-z]"
    )
    private String nickName;

    @Pattern(
            message = "숫자만 가능합니다.",
            regexp = "[0-9]"
    )
    private String phoneNumber;

    @NotNull
    private Gender gender;

    public User toEntity() {
        return User.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .nickName(this.nickName)
                .phoneNumber(this.phoneNumber)
                .gender(this.gender)
                .build();
    }

    public UserDTO(String email,
                   String password,
                   String name,
                   String nickName,
                   String phoneNumber,
                   Gender gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }
}
