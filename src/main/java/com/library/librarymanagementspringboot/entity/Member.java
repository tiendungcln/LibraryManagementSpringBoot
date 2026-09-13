package com.library.librarymanagementspringboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "members")
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String phone;

    @NotBlank
    @Column(nullable = false)
    private String address;

    @NotNull
    @Column(name = "registered_date", nullable = false)
    private LocalDate registeredDate;

    @OneToMany(mappedBy = "member")
    private List<Borrow> borrows;

    @PrePersist
    public void prePersist() {
        registeredDate = LocalDate.now();
    }

    public Member(){}

}
