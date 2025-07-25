package com.unear.pos.membership.repository;

import com.unear.pos.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByBarcodeNumber(String barcodeNumber);

    Optional<Member> findByTel(String tel);
}
