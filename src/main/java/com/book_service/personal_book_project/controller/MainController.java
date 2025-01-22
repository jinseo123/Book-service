package com.book_service.personal_book_project.controller;

import com.book_service.personal_book_project.domain.user.UserCreateForm;
import com.book_service.personal_book_project.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/")
    public String main(){
        return "main";
    }

    @GetMapping("/auth/login")
    public String login_get(){
        return "login";
    }

    //회원가입
    @GetMapping("/auth/register")
    public String register_get(UserCreateForm userCreateForm,Model model){
        return "register";
    }

    @PostMapping("/auth/register")
    public String register_post(@Valid UserCreateForm userCreateForm, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            return "register";
        }

        if(!userCreateForm.getPassword().equals(userCreateForm.getPassword_check())){ //비번 비번확인 다르면
            bindingResult.rejectValue("password_check","passwordInCorrect","비밀번호가 일치하지 않습니다.");
            log.info("***********************************************************************비번 일치 x ");
            return "register";
        }
        userService.create(userCreateForm.getNickname(),userCreateForm.getEmail(),userCreateForm.getPassword());
        return "redirect:/"; //메인화면 리다이렉트 회원가입 성공 !
    }
}
