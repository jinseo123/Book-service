package com.book_service.personal_book_project.domain.memo;

import com.book_service.personal_book_project.domain.book.Book;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memo_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_id")
    @NotNull
    private Book book;

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime saved;
}
