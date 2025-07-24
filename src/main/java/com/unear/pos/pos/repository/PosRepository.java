package com.unear.pos.pos.repository;

import com.unear.pos.pos.entity.Pos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosRepository extends JpaRepository<Pos, Long> {
}
