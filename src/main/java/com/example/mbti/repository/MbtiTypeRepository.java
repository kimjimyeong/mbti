package com.example.mbti.repository;

import com.example.mbti.model.entity.MbtiType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MbtiTypeRepository extends JpaRepository<MbtiType, Long> {
    Optional<MbtiType> findByDeveloper(String Developer);
}
