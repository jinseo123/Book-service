package com.book_service.personal_book_project.domain.memo;

import com.book_service.personal_book_project.domain.book.Book;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemoDto {

    private Long memo_id;

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime saved;
}
