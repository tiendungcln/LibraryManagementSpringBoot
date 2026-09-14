package com.library.librarymanagementspringboot.service;

import com.library.librarymanagementspringboot.dto.BorrowResponseDTO;
import com.library.librarymanagementspringboot.dto.MemberPatchDTO;
import com.library.librarymanagementspringboot.dto.MemberRequestDTO;
import com.library.librarymanagementspringboot.dto.MemberResponseDTO;
import com.library.librarymanagementspringboot.entity.Borrow;
import com.library.librarymanagementspringboot.entity.Member;
import com.library.librarymanagementspringboot.exception.ResourceNotFoundException;
import com.library.librarymanagementspringboot.repository.BorrowRepository;
import com.library.librarymanagementspringboot.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BorrowRepository borrowRepository;

    public MemberService(MemberRepository memberRepository, BorrowRepository borrowRepository) {
        this.memberRepository = memberRepository;
        this.borrowRepository = borrowRepository;
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

    private BorrowResponseDTO toResponseBorrow(Borrow borrow){
        BorrowResponseDTO response = new BorrowResponseDTO();

        response.setBorrowId(borrow.getBorrowId());
        response.setMemberId(borrow.getMember().getMemberId());
        response.setBookId(borrow.getBook().getBookId());
        response.setBorrowedAt(borrow.getBorrowedAt());
        response.setReturnedAt(borrow.getReturnedAt());

        return response;
    }

    public Page<MemberResponseDTO> getAllMembers(Pageable pageable){
        return memberRepository.findAll(pageable)
                .map(this::toResponse);

    }

    public MemberResponseDTO getMemberById(Long id){
        Member member = memberRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Member not found with id: " + id
                        )
                );

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
        Member member = memberRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Member not found with id: " + id
                        )
                );

        if (request.getName() != null){
            if (request.getName().isBlank()){
                throw new IllegalArgumentException("Name cannot be blank");
            }

            member.setName(request.getName());
        }

        if (request.getPhone() != null){
            if (request.getPhone().isBlank()){
                throw new IllegalArgumentException("Phone cannot be blank");
            }

            member.setPhone(request.getPhone());
        }

        if (request.getAddress() != null){
            if (request.getAddress().isBlank()){
                throw new IllegalArgumentException("Address cannot be blank");
            }

            member.setAddress(request.getAddress());
        }

        Member savedMember = memberRepository.save(member);

        return toResponse(savedMember);
    }

    public void deleteMember(Long id){
        if (!memberRepository.existsById(id)){
            throw new ResourceNotFoundException(
                    "Member not found with id: " + id
            );
        }

        memberRepository.deleteById(id);
    }

    public List<MemberResponseDTO> searchMemberByName(String name){
        return memberRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public MemberResponseDTO searchMemberByPhone(String phone){
        Member member = memberRepository.findByPhone(phone)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Member not found with phone: " + phone
                        )
                );

        return toResponse(member);
    }

    public List<BorrowResponseDTO> searchBorrowsByMemberId(Long memberId){
        if(!memberRepository.existsById(memberId)){
            throw new ResourceNotFoundException(
                    "Member not found with id: " + memberId
            );
        }

        return borrowRepository.findByMemberMemberId(memberId)
                .stream()
                .map(this::toResponseBorrow)
                .toList();
    }

    public List<BorrowResponseDTO> searchBorrowsByPhone(String phone){
        Member member = memberRepository.findByPhone(phone)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Member not found with phone: " + phone
                        )
                );

        return borrowRepository.findByMemberPhone(phone)
                .stream()
                .map(this::toResponseBorrow)
                .toList();
    }

}
