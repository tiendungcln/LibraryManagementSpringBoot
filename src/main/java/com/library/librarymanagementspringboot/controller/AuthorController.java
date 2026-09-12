package com.library.librarymanagementspringboot.controller;

import com.library.librarymanagementspringboot.dto.AuthorPatchDTO;
import com.library.librarymanagementspringboot.dto.AuthorRequestDTO;
import com.library.librarymanagementspringboot.dto.AuthorResponseDTO;
import com.library.librarymanagementspringboot.dto.BookResponseDTO;
import com.library.librarymanagementspringboot.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorResponseDTO> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public AuthorResponseDTO getAuthorById(@PathVariable Long id){
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public AuthorResponseDTO createAuthor(@RequestBody AuthorRequestDTO request){
        return authorService.createAuthor(request);
    }

    @PatchMapping("/{id}")
    public AuthorResponseDTO updateAuthor(@PathVariable Long id, @RequestBody AuthorPatchDTO request){
        return authorService.updateAuthor(id, request);
    }

    @DeleteMapping("/{id}")
    public boolean deleteAuthor(@PathVariable Long id){
        return authorService.deleteAuthor(id);
    }

    @GetMapping("/{authorId}/books")
    public List<BookResponseDTO> getBooksByAuthorId(@PathVariable Long authorId){
        return authorService.getBooksByAuthorId(authorId);
    }

}
