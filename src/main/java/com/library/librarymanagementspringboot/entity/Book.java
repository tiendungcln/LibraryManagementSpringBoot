package com.library.librarymanagementspringboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity // JPA (Java Persistence API): đây là Entity
@Table(name = "books") // JPA: tên bảng
@Getter // Lombok: tự tạo getter cho tất cả field
@Setter // Lombok: tự tạo setter cho tất cả field
public class Book {

    @Id // Đánh dấu khoá chính (Primary Key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID được tự động sinh bởi Database
    @Column(name = "book_id") // Tên cột trong DB là book_id
    private Long bookId;

    @NotBlank // Không được null, rỗng hoặc chỉ chứa khoảng trắng và chỉ dùng cho String
    @Column(nullable = false) // Database không cho lưu NULL
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false) // @JoinColumn = bên nào quản lý khóa ngoại
    private Author author;

    @NotBlank
    @Column(nullable = false)
    private String publisher;

    @NotNull // Validation: giá trị không được là null khi dữ liệu được kiểm tra ở Java/Spring
    @Column(name = "publish_date", nullable = false) // Database: cột này không được phép lưu giá trị NULL
    private LocalDate publishDate;

    @NotNull
    @PositiveOrZero // Giá phải >= 0, không được âm
    @Column(nullable = false)
    private BigDecimal price;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String isbn;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Integer quantity;

    public Book(){}

    public Book(Long bookId, String title, Author author, String publisher, LocalDate publishDate, BigDecimal price, String isbn, Integer quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.price = price;
        this.isbn = isbn;
        this.quantity = quantity;
    }

    public Book(String title, Author author, String publisher, LocalDate publishDate, BigDecimal price, String isbn, Integer quantity) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.price = price;
        this.isbn = isbn;
        this.quantity = quantity;
    }

}
