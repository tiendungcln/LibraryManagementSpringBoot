package com.library.librarymanagementspringboot.repository;

import com.library.librarymanagementspringboot.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
