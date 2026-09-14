package com.library.librarymanagementspringboot.controller;

import com.library.librarymanagementspringboot.dto.AuthorPatchDTO;
import com.library.librarymanagementspringboot.dto.AuthorRequestDTO;
import com.library.librarymanagementspringboot.dto.AuthorResponseDTO;
import com.library.librarymanagementspringboot.dto.BookResponseDTO;
import com.library.librarymanagementspringboot.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Page<AuthorResponseDTO>> getAllAuthors(Pageable pageable){
        return ResponseEntity.ok(
                authorService.getAllAuthors(pageable)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable Long id){
        return ResponseEntity.ok(
                authorService.getAuthorById(id)
        );
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> createAuthor(@Valid @RequestBody AuthorRequestDTO request){
        AuthorResponseDTO response = authorService.createAuthor(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorPatchDTO request){
        return ResponseEntity.ok(
                authorService.updateAuthor(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{authorId}/books")
    public ResponseEntity<List<BookResponseDTO>> getBooksByAuthorId(@PathVariable Long authorId){
        return ResponseEntity.ok(
                authorService.getBooksByAuthorId(authorId)
        );
    }

}
