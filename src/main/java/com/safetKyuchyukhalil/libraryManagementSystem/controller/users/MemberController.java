package com.safetKyuchyukhalil.libraryManagementSystem.controller.users;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.books.Book;
import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Member;
import com.safetKyuchyukhalil.libraryManagementSystem.service.borrowing.BorrowingService;
import com.safetKyuchyukhalil.libraryManagementSystem.service.users.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    private final BorrowingService borrowingService;


    public MemberController(MemberService memberService, BorrowingService borrowingService) {
        this.memberService = memberService;
        this.borrowingService = borrowingService;
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Member member = memberService.findById(id);

        return ResponseEntity.ok(member);
    }

    @GetMapping("/borrowed-books")
    public ResponseEntity<List<Book>> getBorrowedBooksByMember(@PathVariable Long memberId) {
        List<Book> borrowedBooks = borrowingService.findBorrowedBooksByMember(memberId);

        return ResponseEntity.ok(borrowedBooks);
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member createdMember = memberService.save(member);

        return ResponseEntity.status(201).body(createdMember);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
