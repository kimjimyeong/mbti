package com.example.mbti.service;

import com.example.mbti.model.dto.ResultDto;
import com.example.mbti.model.entity.MbtiType;
import com.example.mbti.repository.MbtiTestRepository;
import com.example.mbti.repository.MbtiTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MbtiService {

    private final MbtiTypeRepository mbtiTypeRepository;
    private final MbtiTestRepository mbtiTestRepository;


    public MbtiService(MbtiTypeRepository mbtiTypeRepository, MbtiTestRepository mbtiTestRepository) {
        this.mbtiTypeRepository = mbtiTypeRepository;
        this.mbtiTestRepository = mbtiTestRepository;
    }

    public MbtiType result(ResultDto resultDto){
        List<Integer> result = resultDto.getResults();
        String M = (result.get(0) / 100 > result.get(0) % 100) ? "E" : "I";
        String B = (result.get(1) / 100 > result.get(1) % 100) ? "S" : "N";
        String T = (result.get(2) / 100 > result.get(2) % 100) ? "T" : "F";
        String I = (result.get(3) / 100 > result.get(3) % 100) ? "J" : "P";

        String mbti = M + B + T + I;
        log.info("mbti -> {}", mbti);
        return mbtiTypeRepository.findByType(mbti);
    }

    


}
