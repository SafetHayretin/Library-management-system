package com.safetKyuchyukhalil.libraryManagementSystem.controller.users;

import com.safetKyuchyukhalil.libraryManagementSystem.DTOs.books.BookResponse;
import com.safetKyuchyukhalil.libraryManagementSystem.DTOs.users.MemberResponse;
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
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        List<Member> members = memberService.findAll();
        List<MemberResponse> memberResponses = members.stream().map(Member::toResponse).toList();

        return ResponseEntity.ok(memberResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable Long id) {
        Member member = memberService.findById(id);

        return ResponseEntity.ok(member.toResponse());
    }

    @GetMapping("/borrowed-books")
    public ResponseEntity<List<BookResponse>> getBorrowedBooksByMember(@PathVariable Long memberId) {
        List<Book> borrowedBooks = borrowingService.findBorrowedBooksByMember(memberId);
        List<BookResponse> bookResponses = borrowedBooks.stream().map(Book::toResponse).toList();

        return ResponseEntity.ok(bookResponses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
