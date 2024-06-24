package com.safetKyuchyukhalil.libraryManagementSystem.repository;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  BookRepository extends JpaRepository<Book, Long> {
    // Custom query methods (if needed)
}
