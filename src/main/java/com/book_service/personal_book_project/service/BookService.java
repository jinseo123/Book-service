package com.book_service.personal_book_project.service;

import com.book_service.personal_book_project.domain.book.Book;
import com.book_service.personal_book_project.domain.user.Users;
import com.book_service.personal_book_project.dto.BookDto;
import com.book_service.personal_book_project.repository.BookRepository;
import com.book_service.personal_book_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public boolean saveBook(BookDto bookDto){
        Users currentUser = getCurrentUser();
        boolean bookExists = bookRepository.existsByUserIdAndIsbn(currentUser.getId(), bookDto.getIsbn());
        if (bookExists){ // 해당 유저에 해당 isbn이 존재하면 ->이미 저장된 것
            return false; // 저장하지 않고 false 반환
        }
        log.info("**********현재 사용자 : " + currentUser);
        Book book = Book.builder()
                .title(bookDto.getTitle())
                .cover(bookDto.getCover())
                .author(bookDto.getAuthor())
                .publisher(bookDto.getPublisher())
                .bookStatus(bookDto.getBookStatus())
                .startDate(bookDto.getStartDate())
                .endDate(bookDto.getEndDate())
                .isbn(bookDto.getIsbn())
                .description(bookDto.getDescription())
                .user(currentUser)
                .build();
        bookRepository.save(book);
        System.out.println("ddd");
        return true; // 저장 성공
    }
    private Users getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        String username = principal.getUsername();
        log.info(username);
        return userRepository.findByEmail(username).get();
    }
}
