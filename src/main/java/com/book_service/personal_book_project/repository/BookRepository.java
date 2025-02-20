package com.book_service.personal_book_project.repository;

import com.book_service.personal_book_project.domain.book.Book;
import com.book_service.personal_book_project.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    //해당 isbn 의 책을 저장햇는지 확인
    boolean existsByUserIdAndIsbn(Long userId, String isbn);
    List<Book> findByUser_Id(Long UserId);
    Optional<Book> findByUserAndIsbn(Users user, String isbn);
}
