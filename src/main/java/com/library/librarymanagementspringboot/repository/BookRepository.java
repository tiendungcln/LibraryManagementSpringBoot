package com.library.librarymanagementspringboot.repository;

import com.library.librarymanagementspringboot.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorAuthorId(Long authorId);
    List<Book> findByTitleContainingIgnoreCase(String title);
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByQuantityGreaterThan(Integer quantity);
    List<Book> findByQuantity(Integer quantity);
}

// findByQuantity(?)
//        ↓
// quantity = ?

// findByQuantityGreaterThan(?)
//        ↓
// quantity > ?

// findByQuantityLessThan(?)
//        ↓
// quantity < ?