package com.library.librarymanagementspringboot.repository;

import com.library.librarymanagementspringboot.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
