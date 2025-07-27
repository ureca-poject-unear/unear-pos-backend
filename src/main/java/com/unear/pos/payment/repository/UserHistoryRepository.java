package com.unear.pos.payment.repository;

import com.unear.pos.payment.entity.UserHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
    List<UserHistory> findByUserIdOrderByPaidAtDesc(Long userId);

    List<UserHistory> findByPlaceIdOrderByPaidAtDesc(Long placeId);
}
