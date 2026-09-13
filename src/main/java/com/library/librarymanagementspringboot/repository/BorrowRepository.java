package com.library.librarymanagementspringboot.repository;

import com.library.librarymanagementspringboot.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
}
