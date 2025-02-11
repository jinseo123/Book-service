package com.book_service.personal_book_project.service;

import com.book_service.personal_book_project.domain.user.Users;
import com.book_service.personal_book_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Users create(String nickname,String email, String password){ //회원가입
        Users user = new Users();
        user.setEmail(email);
        user.setNickname(nickname);
        user.setPassword(passwordEncoder.encode(password)); //비밀번호 암호화
        userRepository.save(user);
        return user;
    }


}
