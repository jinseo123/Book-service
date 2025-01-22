package com.book_service.personal_book_project.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm { //dto 오류검증
    @Size(min = 2, max = 20)
    @NotEmpty(message = "닉네임을 입력해주세요.")
    private String nickname;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotEmpty(message = "비밀번호 확인 칸을 입력해주세요.")
    private String password_check;

    @NotEmpty(message = "이메일을 입력해주세요.")
    @Email
    private String email;
}
