package com.library.librarymanagementspringboot.controller;

import com.library.librarymanagementspringboot.dto.BorrowRequestDTO;
import com.library.librarymanagementspringboot.dto.BorrowResponseDTO;
import com.library.librarymanagementspringboot.service.BorrowService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrows")
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping
    public ResponseEntity<Page<BorrowResponseDTO>> getAllBorrows(Pageable pageable){
        return ResponseEntity.ok(
                borrowService.getAllBorrows(pageable)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowResponseDTO> getBorrowById(@PathVariable Long id){
        return ResponseEntity.ok(
                borrowService.getBorrowById(id)
        );
    }

    @PostMapping
    public ResponseEntity<BorrowResponseDTO> createBorrow(@Valid @RequestBody BorrowRequestDTO request){
        BorrowResponseDTO response = borrowService.borrowBook(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrow(@PathVariable Long id){
        borrowService.deleteBorrow(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/return")
    public ResponseEntity<BorrowResponseDTO> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(
                borrowService.returnBook(id)
        );
    }

}
