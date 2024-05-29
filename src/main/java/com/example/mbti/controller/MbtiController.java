package com.example.mbti.controller;

import com.example.mbti.model.dto.ResultDto;
import com.example.mbti.model.dto.ResultResponseDto;
import com.example.mbti.model.entity.MbtiTest;
import com.example.mbti.repository.MbtiTestRepository;
import com.example.mbti.service.MbtiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<MbtiTest>> getAllMbtiTests() {
        List<MbtiTest> mbtiTests = mbtiService.getAllMbtiTests();
        return ResponseEntity.ok(mbtiTests);
    }

    @PostMapping("/submit")
    public ResponseEntity<Void> submitAnswers(@RequestBody ResultDto resultDto){
        mbtiService.processAnswers(resultDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/result")
    public ResponseEntity<ResultResponseDto> getResult(){
        ResultResponseDto result = mbtiService.calculateResult();
        return ResponseEntity.ok(result);
    }

}
