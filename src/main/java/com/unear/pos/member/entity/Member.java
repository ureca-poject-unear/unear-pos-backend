package com.unear.pos.member.entity;


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
    private String name;
    private String membershipCode;
    private String barcodeNumber;
    private String tel;

}
