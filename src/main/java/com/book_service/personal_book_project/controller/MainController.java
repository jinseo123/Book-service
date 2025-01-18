package com.book_service.personal_book_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    @GetMapping("/")
    public String main(){
        return "main";
    }

    @PostMapping("/auth/login")
    public String login(){
        return "xxx";
    }

    @GetMapping("/auth/login")
    public String login_get(){
        return "login";
    }
    @GetMapping("/auth/register")
    public String register_get(){
        return "register";
    }
}
