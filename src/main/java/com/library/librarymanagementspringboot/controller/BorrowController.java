package com.library.librarymanagementspringboot.controller;

import com.library.librarymanagementspringboot.dto.BorrowRequestDTO;
import com.library.librarymanagementspringboot.dto.BorrowResponseDTO;
import com.library.librarymanagementspringboot.service.BorrowService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrows")
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping
    public Page<BorrowResponseDTO> getAllBorrows(Pageable pageable){
        return borrowService.getAllBorrows(pageable);
    }

    @GetMapping("/{id}")
    public BorrowResponseDTO getBorrowById(@PathVariable Long id){
        return borrowService.getBorrowById(id);
    }

    @PostMapping
    public BorrowResponseDTO createBorrow(@Valid @RequestBody BorrowRequestDTO request){
        return borrowService.borrowBook(request);
    }

    @DeleteMapping("/{id}")
    public boolean deleteBorrow(@PathVariable Long id){
        return borrowService.deleteBorrow(id);
    }

    @PatchMapping("/{id}/return")
    public BorrowResponseDTO returnBook(@PathVariable Long id) {
        return borrowService.returnBook(id);
    }

}
