package com.library.librarymanagementspringboot.service;

import com.library.librarymanagementspringboot.dto.MemberPatchDTO;
import com.library.librarymanagementspringboot.dto.MemberRequestDTO;
import com.library.librarymanagementspringboot.dto.MemberResponseDTO;
import com.library.librarymanagementspringboot.entity.Member;
import com.library.librarymanagementspringboot.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    private MemberResponseDTO toResponse(Member member){
        MemberResponseDTO response = new MemberResponseDTO();

        response.setMemberId(member.getMemberId());
        response.setName(member.getName());
        response.setPhone(member.getPhone());
        response.setAddress(member.getAddress());
        response.setRegisteredDate(member.getRegisteredDate());

        return response;
    }

    public List<MemberResponseDTO> getAllMembers(){
        return memberRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public MemberResponseDTO getMemberById(Long id){
        Member member = memberRepository.findById(id).orElse(null);

        if (member == null){
            return null;
        }

        return toResponse(member);
    }

    public MemberResponseDTO createMember(MemberRequestDTO request){
        Member member = new Member();

        member.setName(request.getName());
        member.setPhone(request.getPhone());
        member.setAddress(request.getAddress());

        Member savedMember = memberRepository.save(member);

        return toResponse(savedMember);
    }

    public MemberResponseDTO updateMember(Long id, MemberPatchDTO request){
        Member member = memberRepository.findById(id).orElse(null);

        if (member == null){
            return null;
        }

        if (request.getName() != null){
            member.setName(request.getName());
        }

        if (request.getPhone() != null){
            member.setPhone(request.getPhone());
        }

        if (request.getAddress() != null){
            member.setAddress(request.getAddress());
        }

        Member savedMember = memberRepository.save(member);

        return toResponse(savedMember);
    }

    public boolean deleteMember(Long id){
        if (!memberRepository.existsById(id)){
            return false;
        }

        memberRepository.deleteById(id);
        return true;
    }

}
