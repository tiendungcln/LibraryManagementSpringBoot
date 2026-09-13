package com.library.librarymanagementspringboot.repository;

import com.library.librarymanagementspringboot.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findByMemberMemberId(Long memberId); // Borrow.member.memberId = memberId
    List<Borrow> findByMemberPhone(String phone); // Borrow.member.phone = phone
}

// ContainingIgnoreCase: Tìm chuỗi có chứa từ khóa, không phân biệt chữ hoa/chữ thường
// List: Dùng khi kết quả có thể có nhiều object (0 hoặc nhiều)

// IgnoreCase: Tìm chính xác, không phân biệt chữ hoa/chữ thường
// Optional: Dùng khi kết quả có thể có 0 hoặc 1 object