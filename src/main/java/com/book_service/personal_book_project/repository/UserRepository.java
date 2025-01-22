package com.book_service.personal_book_project.repository;

import com.book_service.personal_book_project.domain.user.Users;
import com.book_service.personal_book_project.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email); // 이메일로 사용자 정보 가져오기
}
