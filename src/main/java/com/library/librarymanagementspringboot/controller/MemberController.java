package com.library.librarymanagementspringboot.controller;

import com.library.librarymanagementspringboot.dto.MemberPatchDTO;
import com.library.librarymanagementspringboot.dto.MemberRequestDTO;
import com.library.librarymanagementspringboot.dto.MemberResponseDTO;
import com.library.librarymanagementspringboot.service.MemberService;
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
    public List<MemberResponseDTO> getAllMembers(){
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public MemberResponseDTO getMemberById(@PathVariable Long id){
        return memberService.getMemberById(id);
    }

    @PostMapping
    public MemberResponseDTO createMember(@RequestBody MemberRequestDTO request){
        return memberService.createMember(request);
    }

    @PatchMapping("/{id}")
    public MemberResponseDTO updateMember(@PathVariable Long id,@RequestBody MemberPatchDTO request){
        return memberService.updateMember(id, request);
    }

    @DeleteMapping("/{id}")
    public boolean deleteMember(@PathVariable Long id){
        return memberService.deleteMember(id);
    }

}
