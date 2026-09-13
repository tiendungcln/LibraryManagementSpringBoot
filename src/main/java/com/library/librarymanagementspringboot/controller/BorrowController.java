package com.library.librarymanagementspringboot.controller;

import com.library.librarymanagementspringboot.dto.BorrowPatchDTO;
import com.library.librarymanagementspringboot.dto.BorrowRequestDTO;
import com.library.librarymanagementspringboot.dto.BorrowResponseDTO;
import com.library.librarymanagementspringboot.service.BorrowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrows")
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping
    public List<BorrowResponseDTO> getAllBorrows(){
        return borrowService.getAllBorrows();
    }

    @GetMapping("/{id}")
    public BorrowResponseDTO getBorrowById(@PathVariable Long id){
        return borrowService.getBorrowById(id);
    }

    @PostMapping
    public BorrowResponseDTO createBorrow(@RequestBody BorrowRequestDTO request){
        return borrowService.createBorrow(request);
    }

    @PatchMapping("/{id}")
    public BorrowResponseDTO updateBorrow(@PathVariable Long id, @RequestBody BorrowPatchDTO request){
        return borrowService.updateBorrow(id, request);
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
