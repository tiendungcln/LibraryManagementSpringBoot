package com.library.librarymanagementspringboot.service;

import com.library.librarymanagementspringboot.dto.*;
import com.library.librarymanagementspringboot.entity.Book;
import com.library.librarymanagementspringboot.entity.Borrow;
import com.library.librarymanagementspringboot.entity.Member;
import com.library.librarymanagementspringboot.repository.BookRepository;
import com.library.librarymanagementspringboot.repository.BorrowRepository;
import com.library.librarymanagementspringboot.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Page<BorrowResponseDTO> getAllBorrows(Pageable pageable){
        return borrowRepository.findAll(pageable)
                .map(this::toResponse);
    }

    public BorrowResponseDTO getBorrowById(Long id){
        Borrow borrow = borrowRepository.findById(id).orElse(null);

        if (borrow == null){
            return null;
        }

        return toResponse(borrow);
    }

    @Transactional
    public BorrowResponseDTO borrowBook(BorrowRequestDTO request){
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

        if (book.getQuantity() <= 0){
            return null;
        }

        borrow.setBook(book);
        borrow.setBorrowedAt(LocalDateTime.now());

        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        Borrow savedBorrow = borrowRepository.save(borrow);

        return toResponse(savedBorrow);
    }

    @Transactional
    public BorrowResponseDTO returnBook(Long id){
        Borrow borrow = borrowRepository.findById(id).orElse(null);

        if (borrow == null){
            return null;
        }

        // K cho trả lần 2
        if (borrow.getReturnedAt() != null){
            return null;
        }

        borrow.setReturnedAt(LocalDateTime.now());

        Book book = borrow.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);

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

}
