package com.example.mbti.repository;

import com.example.mbti.model.entity.MbtiType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MbtiTypeRepository extends JpaRepository<MbtiType, Long> {
    MbtiType findByType(String mbti);
}
