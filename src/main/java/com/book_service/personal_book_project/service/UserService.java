package com.book_service.personal_book_project.service;

import com.book_service.personal_book_project.domain.user.User;
import com.book_service.personal_book_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
/*    public User create(String email,String password,String nickname){
        User user = new User();
        user.setEmail(email);
        user.setNickname(nickname);
        user.setPassword(password);

    }*/
}
