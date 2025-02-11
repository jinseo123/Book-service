package com.book_service.personal_book_project.dto;

import com.book_service.personal_book_project.domain.BookStatus;
import com.book_service.personal_book_project.domain.user.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {

    @NotNull
    private String title;
    @NotNull
    private String cover;
    @NotNull
    private String author;
    @NotNull
    private String publisher;

    @NotNull
    private BookStatus bookStatus;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull
    private String isbn;
}
