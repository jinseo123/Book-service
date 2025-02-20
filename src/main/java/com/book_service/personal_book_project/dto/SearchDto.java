package com.book_service.personal_book_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchDto {
    public Integer totalResults; //전체 검색 결과

    @JsonProperty("item")
    List<Book> item = new ArrayList<>();

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Book{
        public String title;
        public String cover; //커버이미지
        public String author;
        public String publisher;
        public String isbn;
//        public String description;
    }
}
