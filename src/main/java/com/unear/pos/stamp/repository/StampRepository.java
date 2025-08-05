package com.unear.pos.stamp.repository;

import com.unear.pos.stamp.entity.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StampRepository extends JpaRepository<Stamp, Long> {
    boolean existsByUserIdAndEventPlaceId(Long userId, Long eventPlaceId);

    int countByUserIdAndUnearEventId(Long userId, Long unearEventId);
}