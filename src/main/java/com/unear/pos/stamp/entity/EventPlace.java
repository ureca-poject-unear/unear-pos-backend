package com.unear.pos.stamp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "event_places")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EventPlace {

    @Id
    @Column(name = "event_place_id")
    private Long eventPlaceId;

    @Column(name = "unear_event_id", nullable = false)
    private Long unearEventId;

    @Column(name = "place_id", nullable = false)
    private Long placeId;

    @Column(name = "event_code", nullable = false)
    private String eventCode;
}