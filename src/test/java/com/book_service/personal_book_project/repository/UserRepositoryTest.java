package com.book_service.personal_book_project.repository;

import com.book_service.personal_book_project.domain.user.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void save(){
        Users user = new Users();
        user.setPassword("sss");
        user.setEmail("eee");
        user.setNickname("ddd");
        userRepository.save(user);
    }

}