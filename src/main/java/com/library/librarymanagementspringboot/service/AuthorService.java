package com.library.librarymanagementspringboot.service;

import com.library.librarymanagementspringboot.dto.AuthorPatchDTO;
import com.library.librarymanagementspringboot.dto.AuthorRequestDTO;
import com.library.librarymanagementspringboot.dto.AuthorResponseDTO;
import com.library.librarymanagementspringboot.dto.BookResponseDTO;
import com.library.librarymanagementspringboot.entity.Author;
import com.library.librarymanagementspringboot.entity.Book;
import com.library.librarymanagementspringboot.exception.ResourceNotFoundException;
import com.library.librarymanagementspringboot.repository.AuthorRepository;
import com.library.librarymanagementspringboot.repository.BookRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    private AuthorResponseDTO toResponse(Author author){
        AuthorResponseDTO response = new AuthorResponseDTO();

        response.setAuthorId(author.getAuthorId());
        response.setName(author.getName());
        response.setCountry(author.getCountry());
        response.setBirthDate(author.getBirthDate());

        return response;
    }

    private BookResponseDTO toResponseBook(Book book){
        BookResponseDTO response = new BookResponseDTO();

        response.setBookId(book.getBookId());
        response.setTitle(book.getTitle());
        response.setAuthorId(book.getAuthor().getAuthorId());
        response.setAuthorName(book.getAuthor().getName());
        response.setPublisher(book.getPublisher());
        response.setPublishDate(book.getPublishDate());
        response.setPrice(book.getPrice());
        response.setIsbn(book.getIsbn());
        response.setQuantity(book.getQuantity());

        return response;
    }

    public Page<AuthorResponseDTO> getAllAuthors(Pageable pageable){
        return authorRepository.findAll(pageable)
                .map(this::toResponse); // Page đã hỗ trợ nên không cần stream() hay toList()
    }

    public AuthorResponseDTO getAuthorById(Long id){
        Author author = authorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Author not found with id: " + id
                        )
                );

        return toResponse(author);
    }

    public AuthorResponseDTO createAuthor(AuthorRequestDTO request){
        Author author = new Author();

        author.setName(request.getName());
        author.setCountry(request.getCountry());
        author.setBirthDate(request.getBirthDate());

        Author savedAuthor = authorRepository.save(author);

        return toResponse(savedAuthor);
    }

    public AuthorResponseDTO updateAuthor(Long id, AuthorPatchDTO request){
        Author author = authorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Author not found with id: " + id
                        )
                );

        if (request.getName() != null){
            if (request.getName().isBlank()){
                throw new IllegalArgumentException("Name cannot be blank");
            }

            author.setName(request.getName());
        }

        if (request.getCountry() != null){
            if (request.getCountry().isBlank()){
                throw new IllegalArgumentException("Country cannot be blank");
            }

            author.setCountry(request.getCountry());
        }

        if (request.getBirthDate() != null){
            author.setBirthDate(request.getBirthDate());
        }

        Author savedAuthor = authorRepository.save(author);

        return toResponse(savedAuthor);

    }

    public void deleteAuthor(Long id){
        if (!authorRepository.existsById(id)){
            throw new ResourceNotFoundException(
                    "Author not found with id: " + id
            );
        }

        authorRepository.deleteById(id);
    }

    public List<BookResponseDTO> getBooksByAuthorId(Long authorId){
        if (!authorRepository.existsById(authorId)){
            throw new ResourceNotFoundException(
                    "Author not found with id: " + authorId
            );
        }

        return bookRepository.findByAuthorAuthorId(authorId)
                .stream()
                .map(this::toResponseBook)
                .toList();
    }

}
