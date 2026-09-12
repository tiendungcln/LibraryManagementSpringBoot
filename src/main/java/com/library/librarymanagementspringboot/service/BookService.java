package com.library.librarymanagementspringboot.service;

import com.library.librarymanagementspringboot.dto.BookPatchDTO;
import com.library.librarymanagementspringboot.dto.BookRequestDTO;
import com.library.librarymanagementspringboot.dto.BookResponseDTO;
import com.library.librarymanagementspringboot.entity.Author;
import com.library.librarymanagementspringboot.entity.Book;
import com.library.librarymanagementspringboot.repository.AuthorRepository;
import com.library.librarymanagementspringboot.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    private BookResponseDTO toResponse(Book book){
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

    public List<BookResponseDTO> getAllBooks(){
        return bookRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public BookResponseDTO getBookById(Long id){
        Book book = bookRepository.findById(id).orElse(null);

        if (book == null){
            return null;
        }

        return toResponse(book);
    }

    public BookResponseDTO createBook(BookRequestDTO request){

        Book book = new Book();

        book.setTitle(request.getTitle());

        Author author = authorRepository.findById(request.getAuthorId()).orElse(null);

        if (author == null){
            return null;
        }

        book.setAuthor(author);

        book.setPublisher(request.getPublisher());
        book.setPublishDate(request.getPublishDate());
        book.setPrice(request.getPrice());
        book.setIsbn(request.getIsbn());
        book.setQuantity(request.getQuantity());

        Book savedBook = bookRepository.save(book);

        return toResponse(savedBook);
    }

    public BookResponseDTO updateBook(Long id, BookPatchDTO request){
        Book book = bookRepository.findById(id).orElse(null);

        if (book == null){
            return null;
        }

        if (request.getTitle() != null){
            book.setTitle(request.getTitle());
        }

        if (request.getAuthorId() != null){
            Author author = authorRepository.findById(request.getAuthorId()).orElse(null);

            if (author == null){
                return null;
            }

            book.setAuthor(author);
        }

        if (request.getPublisher() != null){
            book.setPublisher(request.getPublisher());
        }

        if (request.getPublishDate() != null){
            book.setPublishDate(request.getPublishDate());
        }

        if (request.getPrice() != null){
            book.setPrice(request.getPrice());
        }

        if (request.getIsbn() != null){
            book.setIsbn(request.getIsbn());
        }

        if (request.getQuantity() != null){
            book.setQuantity(request.getQuantity());
        }

        Book savedBook = bookRepository.save(book);

        return toResponse(savedBook);
    }

    public boolean deleteBook(Long id){
        if (!bookRepository.existsById(id)){
            return false;
        }

        bookRepository.deleteById(id);
        return true;
    }

}
