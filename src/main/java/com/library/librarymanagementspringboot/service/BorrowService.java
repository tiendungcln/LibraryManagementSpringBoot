package com.library.librarymanagementspringboot.service;

import com.library.librarymanagementspringboot.dto.*;
import com.library.librarymanagementspringboot.entity.Book;
import com.library.librarymanagementspringboot.entity.Borrow;
import com.library.librarymanagementspringboot.entity.Member;
import com.library.librarymanagementspringboot.repository.BookRepository;
import com.library.librarymanagementspringboot.repository.BorrowRepository;
import com.library.librarymanagementspringboot.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public BorrowService(BorrowRepository borrowRepository, MemberRepository memberRepository, BookRepository bookRepository) {
        this.borrowRepository = borrowRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }

    private BorrowResponseDTO toResponse(Borrow borrow){
        BorrowResponseDTO response = new BorrowResponseDTO();

        response.setBorrowId(borrow.getBorrowId());
        response.setMemberId(borrow.getMember().getMemberId());
        response.setBookId(borrow.getBook().getBookId());
        response.setBorrowedAt(borrow.getBorrowedAt());
        response.setReturnedAt(borrow.getReturnedAt());

        return response;
    }

    public List<BorrowResponseDTO> getAllBorrows(){
        return borrowRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public BorrowResponseDTO getBorrowById(Long id){
        Borrow borrow = borrowRepository.findById(id).orElse(null);

        if (borrow == null){
            return null;
        }

        return toResponse(borrow);
    }

    public BorrowResponseDTO createBorrow(BorrowRequestDTO request){
        Borrow borrow = new Borrow();

        Member member = memberRepository.findById(request.getMemberId()).orElse(null);

        if (member == null){
            return null;
        }

        borrow.setMember(member);

        Book book = bookRepository.findById(request.getBookId()).orElse(null);

        if (book == null){
            return null;
        }

        borrow.setBook(book);

        borrow.setBorrowedAt(LocalDateTime.now());

        Borrow savedBorrow = borrowRepository.save(borrow);

        return toResponse(savedBorrow);
    }

    public BorrowResponseDTO updateBorrow(Long id, BorrowPatchDTO request){
        Borrow borrow = borrowRepository.findById(id).orElse(null);

        if (borrow == null){
            return null;
        }

        if (request.getMemberId() != null) {
            Member member = memberRepository.findById(request.getMemberId()).orElse(null);

            if (member == null) {
                return null;
            }

            borrow.setMember(member);
        }

        if (request.getBookId() != null) {
            Book book = bookRepository.findById(request.getBookId()).orElse(null);

            if (book == null) {
                return null;
            }

            borrow.setBook(book);
        }

        if (request.getBorrowedAt() != null) {
            borrow.setBorrowedAt(request.getBorrowedAt());
        }

        if (request.getReturnedAt() != null) {
            borrow.setReturnedAt(request.getReturnedAt());
        }

        Borrow savedBorrow = borrowRepository.save(borrow);

        return toResponse(savedBorrow);
    }

    public boolean deleteBorrow(Long id){
        if (!bookRepository.existsById(id)){
            return false;
        }

        bookRepository.deleteById(id);
        return true;
    }

    public BorrowResponseDTO returnBook(Long id){
        Borrow borrow = borrowRepository.findById(id).orElse(null);

        if (borrow == null){
            return null;
        }

        if (borrow.getReturnedAt() != null){
            return null;
        }

        borrow.setReturnedAt(LocalDateTime.now());

        Borrow savedBorrow = borrowRepository.save(borrow);

        return toResponse(savedBorrow);
    }

}
