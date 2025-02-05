package com.book_service.personal_book_project.service;

import com.book_service.personal_book_project.dto.ItemDto;
import com.book_service.personal_book_project.dto.SearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    @Value("${aladin.ttbkey}")
    private String ttbKey;
    @Value("${aladin.item-url}")
    private String itemUrl;

    private final RestTemplate restTemplate;

    public ItemDto getBookDetail(String isbn13) {
        log.info(isbn13);
        String url = UriComponentsBuilder.fromHttpUrl(itemUrl)
                .queryParam("ttbkey", ttbKey)
                .queryParam("itemIdType","ISBN13")
                .queryParam("ItemId",isbn13)
                .queryParam("output","js")
                .queryParam("Version","20131101")
                .toUriString();

        log.info("****************요청 url : " + url);
        ItemDto response = restTemplate.getForObject(url, ItemDto.class);
        // 이미지 URL을 "big" 크기로 변환
        log.info("---------------------------------- " +response);
        if (response != null && response.getItem() != null) {
            ItemDto.Item item = response.getItem().get(0);  // 첫 번째 아이템
            if (item != null && item.getCover() != null) {
                log.info("Cover: " + item.getCover());
                String bigCover = item.getCover().replace("coversum", "cover500");
                item.setCover(bigCover);  // 커버 URL 업데이트
            }
            log.info("아이템 정보: " + item);
        }
        return response;
    }
}
