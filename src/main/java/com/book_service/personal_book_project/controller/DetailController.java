package com.book_service.personal_book_project.controller;

import com.book_service.personal_book_project.dto.ItemDto;
import com.book_service.personal_book_project.service.ItemService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
@RequiredArgsConstructor
public class DetailController {

    private final ItemService itemService;

    @GetMapping("/item/{isbn13}")
    public String ItemDetail(@PathVariable("isbn13") String isbn13, Model model){
        ItemDto response = itemService.getBookDetail(isbn13);
        log.info("*********************response : " + response);
        // 아이템이 존재할 때, 첫 번째 아이템만 전달 (List로 존재)

        model.addAttribute("item", response.getItem().get(0));  // 첫 번째 아이템을 추가
        return "item_detail";
    }
}
