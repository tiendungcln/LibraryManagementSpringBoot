package com.library.librarymanagementspringboot.controller;

import com.library.librarymanagementspringboot.dto.BookPatchDTO;
import com.library.librarymanagementspringboot.dto.BookRequestDTO;
import com.library.librarymanagementspringboot.dto.BookResponseDTO;
import com.library.librarymanagementspringboot.dto.BorrowResponseDTO;
import com.library.librarymanagementspringboot.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookResponseDTO> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookResponseDTO getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @PostMapping
    public BookResponseDTO createBook(@RequestBody BookRequestDTO request){
        return bookService.createBook(request);
    }

    @PatchMapping("/{id}")
    public BookResponseDTO updateBook(@PathVariable Long id, @RequestBody BookPatchDTO request){
        return bookService.updateBook(id, request);
    }

    @DeleteMapping("/{id}")
    public boolean deleteBook(@PathVariable Long id){
        return bookService.deleteBook(id);
    }

    @GetMapping("/search") // http://localhost:8080/books/search?title=...
    public List<BookResponseDTO> searchBooksByTitle(@RequestParam String title){
        return bookService.searchBooksByTitle(title);
    }

    @GetMapping("/isbn/{isbn}")
    public BookResponseDTO searchBookByIsbn(@PathVariable String isbn){
        return bookService.searchBookByIsbn(isbn);
    }

    @GetMapping("/available")
    public List<BookResponseDTO> getAvailableBooks(){
        return bookService.getAvailableBooks();
    }

    @GetMapping("/unavailable")
    public List<BookResponseDTO> getUnavailableBooks(){
        return bookService.getUnavailableBooks();
    }

    @GetMapping("/{bookId}/borrows")
    public List<BorrowResponseDTO> searchBorrowsByBook(@PathVariable Long bookId){
        return bookService.searchBorrowsByBook(bookId);
    }

}
