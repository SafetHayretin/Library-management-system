package com.safetKyuchyukhalil.libraryManagementSystem.repository.books;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.books.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Custom query methods (if needed)
    @Query("SELECT b FROM Book b WHERE b.availableCopies > 0")
    List<Book> findAllAvailableBooks();
}
