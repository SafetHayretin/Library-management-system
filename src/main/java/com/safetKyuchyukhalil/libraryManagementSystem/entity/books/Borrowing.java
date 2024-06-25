package com.safetKyuchyukhalil.libraryManagementSystem.entity.books;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Member;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id", referencedColumnName = "id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="book_id", referencedColumnName = "id")
    private Book book;

    private LocalDate borrowDate;

    private LocalDate returnDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
