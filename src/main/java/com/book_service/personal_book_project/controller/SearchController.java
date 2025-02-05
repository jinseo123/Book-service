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
                              @RequestParam(defaultValue="Title",name="queryType") String queryType,
                              @RequestParam(defaultValue="20",name="maxResult") int maxResults,
                              @RequestParam(value = "page" , defaultValue = "1") int page,
                              Model model){
        //Pageable 로 처리
        int pageSize = 20;

        SearchDto response = searchService.searchBook(keyword,queryType,pageSize,page);

        int start = (page-1) * pageSize + 1;
        int totalPages = (int) Math.ceil((double) response.getTotalResults() / pageSize);
        // 항상 10개 페이지 유지
        int startPage = ((page - 1) / 10) * 10 + 1;
        int endPage = Math.min(startPage + 9, totalPages);
        // << 와 >> 이동을 위한 블록 설정
        int prevBlockPage = Math.max(1, startPage - 10);
        int nextBlockPage = Math.min(totalPages, startPage + 10);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("prevBlockPage", prevBlockPage);
        model.addAttribute("nextBlockPage", nextBlockPage);

        log.info("Search Response: {}", response);
        model.addAttribute("books",response.getItem());
        model.addAttribute("query",keyword);
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPages",totalPages); // 전체 페이지 수 계산
        model.addAttribute("totalResults", response.getTotalResults()); //총 검색결과 개수
        return "search_result";
    }
}
