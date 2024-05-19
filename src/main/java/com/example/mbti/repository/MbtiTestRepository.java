package com.example.mbti.repository;

import com.example.mbti.model.entity.MbtiTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MbtiTestRepository extends JpaRepository<MbtiTest, Long> {

    @Override
    List<MbtiTest> findAll();
}
