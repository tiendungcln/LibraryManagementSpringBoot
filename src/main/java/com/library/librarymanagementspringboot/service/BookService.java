package com.library.librarymanagementspringboot.service;

import com.library.librarymanagementspringboot.entity.Book;
import com.library.librarymanagementspringboot.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

}
