package com.book_service.personal_book_project.domain.book;

import com.book_service.personal_book_project.domain.user.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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
    private String thumbnail;
    @NotNull
    private String author;
    @NotNull
    private String publisher;

    @NotNull
    private Integer isbn;

}
