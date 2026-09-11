package com.library.librarymanagementspringboot.repository;

import com.library.librarymanagementspringboot.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
