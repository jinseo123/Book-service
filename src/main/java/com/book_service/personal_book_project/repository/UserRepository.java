package com.book_service.personal_book_project.repository;

import com.book_service.personal_book_project.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    
}
