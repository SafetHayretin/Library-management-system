package com.safetKyuchyukhalil.libraryManagementSystem.repository.borrowing;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.books.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
}
