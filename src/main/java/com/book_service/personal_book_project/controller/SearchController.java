package com.book_service.personal_book_project.controller;

import com.book_service.personal_book_project.dto.SearchDto;
import com.book_service.personal_book_project.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/search/result")//keyword=검색내용 , queryType=검색카테고리, 제목,저자 등등
    public String searchBooks(@RequestParam(required = false, defaultValue = "",name="keyword") String keyword,
                              @RequestParam(defaultValue="Title",name="queryType") String queryType, @RequestParam(defaultValue="20",name="maxResult") int maxResults, Model model){
        SearchDto response = searchService.searchBook(keyword,queryType,maxResults);
        log.info("Search Response: {}", response);
        model.addAttribute("books",response.getItem());
        return "search_result";
    }
}
