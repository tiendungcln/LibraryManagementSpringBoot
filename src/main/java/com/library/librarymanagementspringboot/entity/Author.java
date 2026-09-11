package com.library.librarymanagementspringboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long authorId;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String country;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @OneToMany(mappedBy = "author") // mappedBy = bên còn lại nói “tôi không quản lý khóa ngoại, quan hệ này do field bên kia quản lý (quan hệ được quản lý bởi field Book.author)”
    private List<Book> books;

    public Author(){}

    public Author(Long authorId, String name, String country, LocalDate birthDate) {
        this.authorId = authorId;
        this.name = name;
        this.country = country;
        this.birthDate = birthDate;
    }

    public Author(String name, String country, LocalDate birthDate) {
        this.name = name;
        this.country = country;
        this.birthDate = birthDate;
    }

}
