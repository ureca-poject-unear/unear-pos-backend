package com.unear.pos.pos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "pos")
@Getter
public class Pos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long posId;
    private Long placeId;
}

