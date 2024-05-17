package com.example.mbti.controller;

import com.example.mbti.model.dto.ResultDto;
import com.example.mbti.model.entity.MbtiTest;
import com.example.mbti.model.entity.MbtiType;
import com.example.mbti.repository.MbtiTestRepository;
import com.example.mbti.service.MbtiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mbti")
public class MbtiController {

    private final MbtiService mbtiService;
    private final MbtiTestRepository mbtiTestRepository;

    public MbtiController(MbtiService mbtiService, MbtiTestRepository mbtiTestRepository) {
        this.mbtiService = mbtiService;
        this.mbtiTestRepository = mbtiTestRepository;
    }


    @GetMapping("/start")
    public List<MbtiTest> testList() {
        return mbtiTestRepository.findAll();
    }

    @GetMapping("/result")
    public MbtiType result(@RequestBody ResultDto resultDto){
        return mbtiService.result(resultDto);
    }

}
