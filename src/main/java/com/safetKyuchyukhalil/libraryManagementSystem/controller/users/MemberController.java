package com.safetKyuchyukhalil.libraryManagementSystem.controller.users;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.books.Book;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Member;
import com.safetKyuchyukhalil.libraryManagementSystem.service.borrowing.BorrowingService;
import com.safetKyuchyukhalil.libraryManagementSystem.service.users.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private BorrowingService borrowingService;

    @GetMapping
    public List<Member> getAllUsers() {
        return memberService.getAllUsers();
    }

    @GetMapping
    public ResponseEntity<Member> getUserById(@PathVariable Long id) {
        Member member = memberService.getUserById(id);

        return ResponseEntity.ok(member);
    }

    @GetMapping("/borrowed-books")
    public ResponseEntity<List<Book>> getBorrowedBooksByUser(@PathVariable Long userId) {
        List<Book> borrowedBooks = borrowingService.findBorrowedBooksByUser(userId);

        return ResponseEntity.ok(borrowedBooks);
    }

    @PostMapping
    public ResponseEntity<Member> createUser(@RequestBody Member member) {
        Member createdMember = memberService.saveUser(member);

        return ResponseEntity.status(201).body(createdMember);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        memberService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
