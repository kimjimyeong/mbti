package com.example.mbti.repository;

import com.example.mbti.model.entity.MbtiType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MbtiTypeRepository extends JpaRepository<MbtiType, Long> {
}
