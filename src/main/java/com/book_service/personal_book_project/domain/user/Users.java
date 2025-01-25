package com.book_service.personal_book_project.domain.user;

import com.book_service.personal_book_project.domain.book.Book;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Users {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email", nullable = false,unique = true)
    private String email;

    @NotNull
    private String password;

    @NotNull @Column(unique = true)
    private String nickname;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();
}
