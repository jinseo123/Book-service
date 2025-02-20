package com.book_service.personal_book_project.repository;

import com.book_service.personal_book_project.domain.book.Book;
import com.book_service.personal_book_project.domain.memo.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo,Long> {
    List<Memo> findByBook_Id(Long bookId);
}
