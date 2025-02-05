package com.book_service.personal_book_project.service;

import com.book_service.personal_book_project.dto.SearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchService {

    @Value("${aladin.ttbkey}")
    private String ttbKey;
    @Value("${aladin.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public SearchDto searchBook(String keyword, String queryType,int maxResults,int start){
        String url = String.format(
          "%s?ttbkey=%s&Query=%s&QueryType=%s&MaxResults=%d&start=%d&SearchTarget=Book&Output=js&Version=20131101",
                baseUrl,ttbKey,keyword,queryType,maxResults, start
        );
        log.info("****************요청 url : " + url);
        SearchDto searchDto = restTemplate.getForObject(url, SearchDto.class);
        // 이미지 URL을 "big" 크기로 변환
        if (searchDto != null && searchDto.getItem() != null) {
            searchDto.getItem().forEach(book -> {
                if (book.getCover() != null) {
                    String bigCover = book.getCover().replace("coversum", "cover500");
                    book.setCover(bigCover); // 커버 URL 업데이트
                }
            });
        }
        log.info("url 생성 : " + url);
        String response = restTemplate.getForObject(url, String.class);
        log.info("API Response: " + response);
        return searchDto;
    }
}
