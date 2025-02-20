package com.book_service.personal_book_project.controller;

import com.book_service.personal_book_project.dto.BookDto;
import com.book_service.personal_book_project.dto.ItemDto;
import com.book_service.personal_book_project.service.BookService;
import com.book_service.personal_book_project.service.ItemService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class DetailController {

    private final ItemService itemService;
    private final BookService bookService;

    @GetMapping("/item/{isbn}")
    public String ItemDetail(@PathVariable("isbn") String isbn, Model model){
        ItemDto response = itemService.getBookDetail(isbn);
        log.info("*********************response : " + response);
        // 아이템이 존재할 때, 첫 번째 아이템만 전달 (List로 존재)

        model.addAttribute("item", response.getItem().get(0));  // 첫 번째 아이템을 추가
        return "item_detail";
    }
    @PostMapping("/item/{isbn}")
    public ResponseEntity<String> save_Book(@ModelAttribute BookDto bookDto){
        log.info("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& Bookdto:" + bookDto);
        boolean isSaved = bookService.saveBook(bookDto);
        if(!isSaved){
            return ResponseEntity.badRequest().body("이미 이 책은 저장되었습니다.");
        }

        return ResponseEntity.ok("책이 저장되었습니다.");
    }



}
