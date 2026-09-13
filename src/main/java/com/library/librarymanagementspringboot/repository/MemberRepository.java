package com.library.librarymanagementspringboot.repository;

import com.library.librarymanagementspringboot.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByNameContainingIgnoreCase(String name);
    Optional<Member> findByPhone(String phone);
}
