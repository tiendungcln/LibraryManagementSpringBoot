package com.library.librarymanagementspringboot.service;

import com.library.librarymanagementspringboot.dto.BookPatchDTO;
import com.library.librarymanagementspringboot.dto.BookRequestDTO;
import com.library.librarymanagementspringboot.dto.BookResponseDTO;
import com.library.librarymanagementspringboot.dto.BorrowResponseDTO;
import com.library.librarymanagementspringboot.entity.Author;
import com.library.librarymanagementspringboot.entity.Book;
import com.library.librarymanagementspringboot.entity.Borrow;
import com.library.librarymanagementspringboot.exception.ResourceNotFoundException;
import com.library.librarymanagementspringboot.repository.AuthorRepository;
import com.library.librarymanagementspringboot.repository.BookRepository;
import com.library.librarymanagementspringboot.repository.BorrowRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BorrowRepository borrowRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, BorrowRepository borrowRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.borrowRepository = borrowRepository;
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

    private BorrowResponseDTO toResponseBorrow(Borrow borrow){
        BorrowResponseDTO response = new BorrowResponseDTO();

        response.setBorrowId(borrow.getBorrowId());
        response.setMemberId(borrow.getMember().getMemberId());
        response.setBookId(borrow.getBook().getBookId());
        response.setBorrowedAt(borrow.getBorrowedAt());
        response.setReturnedAt(borrow.getReturnedAt());

        return response;
    }

    public Page<BookResponseDTO> getAllBooks(Pageable pageable){
        return bookRepository.findAll(pageable)
                .map(this::toResponse);
    }

    public BookResponseDTO getBookById(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id: " + id
                        )
                );

        return toResponse(book);
    }

    public BookResponseDTO createBook(BookRequestDTO request){

        Book book = new Book();

        book.setTitle(request.getTitle());

        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Author not found with id: " + request.getAuthorId()
                        )
                );

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
        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id: " + id
                        )
                );

        if (request.getTitle() != null){
            if (request.getTitle().isBlank()){
                throw new IllegalArgumentException("Title cannot be blank");
            }

            book.setTitle(request.getTitle());
        }

        if (request.getAuthorId() != null){
            Author author = authorRepository.findById(request.getAuthorId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Author not found with id: " + request.getAuthorId()
                            )
                    );

            book.setAuthor(author);
        }

        if (request.getPublisher() != null){
            if (request.getPublisher().isBlank()){
                throw new IllegalArgumentException("Publisher cannot be blank");
            }

            book.setPublisher(request.getPublisher());
        }

        if (request.getPublishDate() != null){
            book.setPublishDate(request.getPublishDate());
        }

        if (request.getPrice() != null){
            book.setPrice(request.getPrice());
        }

        if (request.getIsbn() != null){
            if (request.getIsbn().isBlank()){
                throw new IllegalArgumentException("ISBN cannot be blank");
            }

            book.setIsbn(request.getIsbn());
        }

        if (request.getQuantity() != null){
            book.setQuantity(request.getQuantity());
        }

        Book savedBook = bookRepository.save(book);

        return toResponse(savedBook);
    }

    public void deleteBook(Long id){
        if (!bookRepository.existsById(id)){
            throw new ResourceNotFoundException(
                    "Book not found with id: " + id
            );
        }

        bookRepository.deleteById(id);
    }

    public List<BookResponseDTO> searchBooksByTitle(String title){
        return bookRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public BookResponseDTO searchBookByIsbn(String isbn){
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with ISBN: " + isbn
                        )
                );

        return toResponse(book);
    }

    public List<BookResponseDTO> getAvailableBooks(){
        return bookRepository.findByQuantityGreaterThan(0)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<BookResponseDTO> getUnavailableBooks(){
        return bookRepository.findByQuantity(0)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<BorrowResponseDTO> searchBorrowsByBook(Long bookId){
        if (!bookRepository.existsById(bookId)) {
            throw new ResourceNotFoundException(
                    "Book not found with id: " + bookId
            );
        }

        return borrowRepository.findByBookBookId(bookId)
                .stream()
                .map(this::toResponseBorrow)
                .toList();
    }

}
