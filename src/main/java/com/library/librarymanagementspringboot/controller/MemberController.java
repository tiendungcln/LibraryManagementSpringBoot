package com.library.librarymanagementspringboot.controller;

import com.library.librarymanagementspringboot.dto.BorrowResponseDTO;
import com.library.librarymanagementspringboot.dto.MemberPatchDTO;
import com.library.librarymanagementspringboot.dto.MemberRequestDTO;
import com.library.librarymanagementspringboot.dto.MemberResponseDTO;
import com.library.librarymanagementspringboot.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<MemberResponseDTO> getAllMembers(Pageable pageable){
        return memberService.getAllMembers(pageable);
    }

    @GetMapping("/{id}")
    public MemberResponseDTO getMemberById(@PathVariable Long id){
        return memberService.getMemberById(id);
    }

    @PostMapping
    public MemberResponseDTO createMember(@Valid @RequestBody MemberRequestDTO request){
        return memberService.createMember(request);
    }

    @PatchMapping("/{id}")
    public MemberResponseDTO updateMember(@PathVariable Long id, @Valid @RequestBody MemberPatchDTO request){
        return memberService.updateMember(id, request);
    }

    @DeleteMapping("/{id}")
    public boolean deleteMember(@PathVariable Long id){
        return memberService.deleteMember(id);
    }

    @GetMapping("/search") // http://localhost:8080/members/search?name=...
    public List<MemberResponseDTO> searchMemberByName(@RequestParam String name){
        return memberService.searchMemberByName(name);
    }

    @GetMapping("/phone/{phone}")
    public MemberResponseDTO searchMemberByPhone(@PathVariable String phone){
        return memberService.searchMemberByPhone(phone);
    }

    @GetMapping("/{memberId}/borrows")
    public List<BorrowResponseDTO> searchBorrowsByMemberId(@PathVariable Long memberId){
        return memberService.searchBorrowsByMemberId(memberId);
    }

    @GetMapping("/phone/{phone}/borrows")
    public List<BorrowResponseDTO> searchBorrowsByPhone(@PathVariable String phone){
        return memberService.searchBorrowsByPhone(phone);
    }

}
