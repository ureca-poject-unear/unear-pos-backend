package com.unear.pos.stamp.repository;

import com.unear.pos.stamp.entity.UnearEvent;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnearEventRepository extends JpaRepository<UnearEvent, Long> {
    Optional<UnearEvent> findByStartAtLessThanEqualAndEndAtGreaterThanEqual(LocalDate startDate, LocalDate endDate);

    default Optional<UnearEvent> findActiveEvent() {
        LocalDate now = LocalDate.now();
        return findByStartAtLessThanEqualAndEndAtGreaterThanEqual(now, now);
    }
}
