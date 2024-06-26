package com.safetKyuchyukhalil.libraryManagementSystem.repository.borrowing;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.borrowing.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

    @Query("Select b from BORROWING b where b.member.id = :id AND b.returnDate IS NULL")
    List<Borrowing> findBorrowedBooksByUser(@Param("id")Long id);

    @Query("SELECT b FROM Borrowing b WHERE b.returnDate < :todayDate")
    List<Borrowing> findAllOverdueBorrowings(@Param("todayDate") LocalDate todayDate);
}
