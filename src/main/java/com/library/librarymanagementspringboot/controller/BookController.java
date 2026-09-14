package com.library.librarymanagementspringboot.controller;

import com.library.librarymanagementspringboot.dto.BookPatchDTO;
import com.library.librarymanagementspringboot.dto.BookRequestDTO;
import com.library.librarymanagementspringboot.dto.BookResponseDTO;
import com.library.librarymanagementspringboot.dto.BorrowResponseDTO;
import com.library.librarymanagementspringboot.service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping // 200 OK
    public ResponseEntity<Page<BookResponseDTO>> getAllBooks(Pageable pageable){
        return ResponseEntity.ok(
                bookService.getAllBooks(pageable)
        );
    }

    @GetMapping("/{id}") // 200 OK
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id){
        return ResponseEntity.ok(
                bookService.getBookById(id)
        );
    }

    @PostMapping // 201 Created
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody BookRequestDTO request){
        BookResponseDTO response = bookService.createBook(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PatchMapping("/{id}") // 200 OK
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @Valid @RequestBody BookPatchDTO request){
        return ResponseEntity.ok(
                bookService.updateBook(id, request)
        );
    }

    @DeleteMapping("/{id}") // 204 No Content
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/search") // http://localhost:8080/books/search?title=... // 200 OK
    public ResponseEntity<List<BookResponseDTO>> searchBooksByTitle(@RequestParam String title){
        return ResponseEntity.ok(
                bookService.searchBooksByTitle(title)
        );
    }

    @GetMapping("/isbn/{isbn}") // 200 OK
    public ResponseEntity<BookResponseDTO> searchBookByIsbn(@PathVariable String isbn){
        return ResponseEntity.ok(
                bookService.searchBookByIsbn(isbn)
        );
    }

    @GetMapping("/available") // 200 OK
    public ResponseEntity<List<BookResponseDTO>> getAvailableBooks(){
        return ResponseEntity.ok(
                bookService.getAvailableBooks()
        );
    }

    @GetMapping("/unavailable") // 200 OK
    public ResponseEntity<List<BookResponseDTO>> getUnavailableBooks(){
        return ResponseEntity.ok(
                bookService.getUnavailableBooks()
        );
    }

    @GetMapping("/{bookId}/borrows") // 200 OK
    public ResponseEntity<List<BorrowResponseDTO>> searchBorrowsByBook(@PathVariable Long bookId){
        return ResponseEntity.ok(
                bookService.searchBorrowsByBook(bookId)
        );
    }

}
