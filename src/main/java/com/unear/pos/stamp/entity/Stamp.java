package com.unear.pos.stamp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stamps")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Stamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stamp_id")
    private Long stampId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "event_place_id", nullable = false)
    private Long eventPlaceId;

    @Column(name = "stamped_at", nullable = false)
    private LocalDateTime stampedAt;

    @Column(name = "event_code", nullable = false)
    private String eventCode;

    @Column(name = "place_name", nullable = false)
    private String placeName;

    @Column(name = "unear_event_id")
    private Long unearEventId;
}