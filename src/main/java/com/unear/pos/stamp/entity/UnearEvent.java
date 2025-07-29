package com.unear.pos.stamp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "unear_events")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UnearEvent {

    @Id
    @Column(name = "unear_event_id")
    private Long unearEventId;

    @Column(name = "coupon_template_id")
    private Long couponTemplateId;

    @Column(name = "event_name", nullable = false)
    private String eventName;

    @Column(name = "event_description")
    private String eventDescription;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "radius_meter")
    private Integer radiusMeter;

    @Column(name = "start_at", nullable = false)
    private LocalDate startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDate endAt;

    @Column(name = "popup_store_id")
    private Long popupStoreId;

    public boolean isActive() {
        LocalDate now = LocalDate.now();
        return !now.isBefore(startAt) && !now.isAfter(endAt);
    }
}