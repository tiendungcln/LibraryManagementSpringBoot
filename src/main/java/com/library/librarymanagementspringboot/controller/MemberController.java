package com.library.librarymanagementspringboot.controller;

import com.library.librarymanagementspringboot.dto.BorrowResponseDTO;
import com.library.librarymanagementspringboot.dto.MemberPatchDTO;
import com.library.librarymanagementspringboot.dto.MemberRequestDTO;
import com.library.librarymanagementspringboot.dto.MemberResponseDTO;
import com.library.librarymanagementspringboot.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<Page<MemberResponseDTO>> getAllMembers(Pageable pageable){
        return ResponseEntity.ok(
                memberService.getAllMembers(pageable)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> getMemberById(@PathVariable Long id){
        return ResponseEntity.ok(
                memberService.getMemberById(id)
        );
    }

    @PostMapping
    public ResponseEntity<MemberResponseDTO> createMember(@Valid @RequestBody MemberRequestDTO request){
        MemberResponseDTO response = memberService.createMember(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> updateMember(@PathVariable Long id, @Valid @RequestBody MemberPatchDTO request){
        return ResponseEntity.ok(
                memberService.updateMember(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search") // http://localhost:8080/members/search?name=...
    public ResponseEntity<List<MemberResponseDTO>> searchMemberByName(@RequestParam String name){
        return ResponseEntity.ok(
                memberService.searchMemberByName(name)
        );
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<MemberResponseDTO> searchMemberByPhone(@PathVariable String phone){
        return ResponseEntity.ok(
                memberService.searchMemberByPhone(phone)
        );
    }

    @GetMapping("/{memberId}/borrows")
    public ResponseEntity<List<BorrowResponseDTO>> searchBorrowsByMemberId(@PathVariable Long memberId){
        return ResponseEntity.ok(
                memberService.searchBorrowsByMemberId(memberId)
        );
    }

    @GetMapping("/phone/{phone}/borrows")
    public ResponseEntity<List<BorrowResponseDTO>> searchBorrowsByPhone(@PathVariable String phone){
        return ResponseEntity.ok(
                memberService.searchBorrowsByPhone(phone)
        );
    }

}
