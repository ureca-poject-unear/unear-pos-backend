package com.unear.pos.place.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "places")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;

    private Long franchiseId;
    private String placeName;
    private String placeDesc;
    private String address;
    private String markerCode;
    private String eventTypeCode;
    private String categoryCode;
    private String benefitCategory;
    private String tel;
    private Integer startTime;
    private Integer endTime;

}
