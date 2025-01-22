package com.book_service.personal_book_project.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Entity
public class Users {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email", nullable = false,unique = true)
    private String email;

    @NotNull
    private String password;

    @NotNull @Column(unique = true)
    private String nickname;


}
