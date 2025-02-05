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
public class ItemDto {

    @JsonProperty("item")
    public List<ItemDto.Item> item;  // item을 List로 변경

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Item {
        private String title;
        public String cover;
        public String author;
        public String publisher;
        public String description;
        public String isbn;
    }
}
