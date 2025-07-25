package com.unear.pos.member.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    private Long userId;

    @Column(nullable = false)
    private String name;
    private String membershipCode;

    @Column(unique = true)
    private String barcodeNumber;

    @Column(nullable = false)
    private String tel;

}
