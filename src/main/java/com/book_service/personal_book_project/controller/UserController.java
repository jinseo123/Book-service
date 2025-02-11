package com.book_service.personal_book_project.controller;

import com.book_service.personal_book_project.domain.book.Book;
import com.book_service.personal_book_project.domain.user.Users;
import com.book_service.personal_book_project.repository.BookRepository;
import com.book_service.personal_book_project.repository.UserRepository;
import com.book_service.personal_book_project.service.BookService;
import com.book_service.personal_book_project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BookService bookService;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    @GetMapping("/myBook")
    public String myBook(Model model, @AuthenticationPrincipal User principal) {
        log.info("---------------------------로그인 정보 :: " + principal.getUsername());
        Users user = userRepository.findByEmail(principal.getUsername()).get();//현재 로그인 user 찾기
        List<Book> bookList = bookRepository.findByUser_Id(user.getId());
        log.info("+++++++++++++++++++++++++++++++++++++++ bookList : {} " , bookList);
        model.addAttribute("user",user);
        model.addAttribute("bookList",bookList); // 책 리스트 전송
        return "myBook";
    }
}
