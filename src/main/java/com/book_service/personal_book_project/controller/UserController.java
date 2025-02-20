package com.book_service.personal_book_project.controller;

import com.book_service.personal_book_project.domain.BookStatus;
import com.book_service.personal_book_project.domain.book.Book;
import com.book_service.personal_book_project.domain.memo.Memo;
import com.book_service.personal_book_project.domain.memo.MemoDto;
import com.book_service.personal_book_project.domain.user.Users;
import com.book_service.personal_book_project.dto.BookDto;
import com.book_service.personal_book_project.repository.BookRepository;
import com.book_service.personal_book_project.repository.MemoRepository;
import com.book_service.personal_book_project.repository.UserRepository;
import com.book_service.personal_book_project.service.BookService;
import com.book_service.personal_book_project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BookService bookService;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final MemoRepository memoRepository;
    @GetMapping("/myBook")
    public String myBook(Model model, @AuthenticationPrincipal User principal) {
        log.info("---------------------------로그인 정보 :: " + principal.getUsername());
        Users user = userRepository.findByEmail(principal.getUsername()).get();//현재 로그인 user 찾기
        List<Book> bookList = bookRepository.findByUser_Id(user.getId());
        log.info("+++++++++++++++++++++++++++++++++++++++ bookList : {} " , bookList);
        model.addAttribute("user",user);
        model.addAttribute("bookList",bookList); // 책 리스트 전송
        return "myBook";
    }

    @GetMapping("/myBook/content/{isbn}") //mypage 책 상세보기
    public String MyBook_detail(@PathVariable("isbn") String isbn, Model model,@AuthenticationPrincipal User principal){
        Users user = userRepository.findByEmail(principal.getUsername()).get();
        Book book =  bookRepository.findByUserAndIsbn(user,isbn).get();
        List<Memo> memoList = memoRepository.findByBook_Id(book.getId());
        model.addAttribute("book",book);
        model.addAttribute("memoList",memoList);
        return "myBookDetail";
    }

    @PostMapping("/submit-rating")
    public  ResponseEntity<String> submitRating(@RequestParam("rating") int rating,@RequestParam("bookId") Long bookId){
        log.info("Received rating ------------------------" + rating);
        Book book =  bookRepository.findById(bookId).get();
        log.info("Received book ------------------------" + book);
        book.setScore(rating);
        bookRepository.save(book);
        return ResponseEntity.ok("별점 등록 성공");
    }
    @PostMapping("/update-status")
    public String update_status(@RequestParam("bookId") Long bookId, @RequestParam("status") BookStatus status){
        Book book = bookRepository.findById(bookId).get();
        if(status == BookStatus.DONE){
            book.setEndDate(LocalDate.now());
        }
        else if(status == BookStatus.READING){
            book.setEndDate(null);
        }
        book.setBookStatus(status);
        bookRepository.save(book);
        return  "redirect:/myBook";
    }
    @PostMapping("/remove-book")
    public String remove_book(@RequestParam("bookId") Long bookId){
        bookRepository.deleteById(bookId);
        return "redirect:/myBook";
    }

    @PostMapping("/save-memo")
    @ResponseBody
    public  ResponseEntity<MemoDto> saveMemo(@RequestParam("memo") String memo, @RequestParam("bookId") Long bookId){
        Book book = bookRepository.findById(bookId).get();
        Memo newMemo = Memo.builder()
                .content(memo)
                .book(book)
                .saved(LocalDateTime.now())
                .build();
        memoRepository.save(newMemo);
        MemoDto memoDto = new MemoDto(newMemo.getMemo_id(), newMemo.getContent(), newMemo.getSaved());
        return ResponseEntity.ok(memoDto);
    }

}
