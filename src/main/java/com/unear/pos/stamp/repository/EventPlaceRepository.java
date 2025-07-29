package com.unear.pos.stamp.repository;

import com.unear.pos.stamp.entity.EventPlace;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventPlaceRepository extends JpaRepository<EventPlace, Long> {
    Optional<EventPlace> findByPlaceId(Long placeId);
}