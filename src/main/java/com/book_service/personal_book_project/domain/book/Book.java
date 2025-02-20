package com.book_service.personal_book_project.domain.book;

import com.book_service.personal_book_project.domain.BookStatus;
import com.book_service.personal_book_project.domain.memo.Memo;
import com.book_service.personal_book_project.domain.user.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    @NotNull
    private Users user;

    @NotNull
    private String title;
    @NotNull
    private String cover;
    @NotNull
    private String author;
    @NotNull
    private String publisher;
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;


    @NotNull// null을 허용하지 않음
    private int score = 0; // 기본값 0 설정

    @NotNull
    private String isbn;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Memo> memos = new ArrayList<>();
}
