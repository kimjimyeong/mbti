package com.example.mbti.service;

import com.example.mbti.model.dto.AnswerDto;
import com.example.mbti.model.dto.ResultDto;
import com.example.mbti.model.dto.ResultResponseDto;
import com.example.mbti.model.entity.MbtiTest;
import com.example.mbti.model.entity.MbtiType;
import com.example.mbti.repository.MbtiTestRepository;
import com.example.mbti.repository.MbtiTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MbtiService {

    private final MbtiTypeRepository mbtiTypeRepository;
    private final MbtiTestRepository mbtiTestRepository;

    private Map<String, Integer> scores;

    public MbtiService(MbtiTypeRepository mbtiTypeRepository, MbtiTestRepository mbtiTestRepository) {
        this.mbtiTypeRepository = mbtiTypeRepository;
        this.mbtiTestRepository = mbtiTestRepository;
        this.scores = new HashMap<>();
        initializeScores();
    }

    private void initializeScores() {
        scores.put("프론트엔드 개발자", 0);
        scores.put("백엔드 개발자", 0);
        scores.put("정보보안 전문가", 0);
        scores.put("인프라(플랫폼) 엔지니어", 0);
        scores.put("게임 개발자", 0);
        scores.put("모바일(안드로이드, ios) 앱 개발자", 0);
        scores.put("데브옵스 개발자", 0);
        scores.put("머신러닝/인공지능(AI) 개발자", 0);
    }

    public List<MbtiTest> getAllMbtiTests(){
        return mbtiTestRepository.findAll();
    }

    public void processAnswers(ResultDto resultDto) {
        initializeScores();
        for (AnswerDto answer : resultDto.getAnswers()) {
            calculateScore(answer.getQuestionId(), answer.getAnswer());
        }
    }

    private void calculateScore(Long questionId, int answer) {
        switch (questionId.intValue()) {
            case 1:
                if (answer == 1) {
                    scores.put("프론트엔드 개발자", scores.get("프론트엔드 개발자") + 3);
                } else if (answer == 2) {
                    scores.put("게임 개발자", scores.get("게임 개발자") + 3);
                }
                break;
            case 2:
                if (answer == 1) {
                    scores.put("프론트엔드 개발자", scores.get("프론트엔드 개발자") + 3);
                    scores.put("백엔드 개발자", scores.get("백엔드 개발자") + 3);
                } else if (answer == 2) {
                    scores.put("데브옵스 개발자", scores.get("데브옵스 개발자") + 3);
                }
                break;
            case 3:
                if (answer == 1) {
                    scores.put("데브옵스 개발자", scores.get("데브옵스 개발자") + 3);
                    scores.put("정보보안 전문가", scores.get("정보보안 전문가") + 3);
                } else if (answer == 2) {
                    scores.put("게임 개발자", scores.get("게임 개발자") + 3);
                }
                break;
            case 4:
                if (answer == 1) {
                    scores.put("게임 개발자", scores.get("게임 개발자") + 3);
                } else if (answer == 2) {
                    scores.put("프론트엔드 개발자", scores.get("프론트엔드 개발자") + 3);
                }
                break;
            case 5:
                if (answer == 1) {
                    scores.put("프론트엔드 개발자", scores.get("프론트엔드 개발자") + 3);
                    scores.put("게임 개발자", scores.get("게임 개발자") + 3);
                } else if (answer == 2) {
                    scores.put("데브옵스 개발자", scores.get("데브옵스 개발자") + 3);
                    scores.put("백엔드 개발자", scores.get("백엔드 개발자") + 3);
                }
                break;
            case 6:
                if (answer == 1) {
                    scores.put("프론트엔드 개발자", scores.get("프론트엔드 개발자") + 2);
                } else if (answer == 2) {
                    scores.put("정보보안 전문가", scores.get("정보보안 전문가") + 3);
                }
                break;
            case 7:
                if (answer == 1) {
                    scores.put("데브옵스 개발자", scores.get("데브옵스 개발자") + 3);
                } else if (answer == 2) {
                    scores.put("프론트엔드 개발자", scores.get("프론트엔드 개발자") + 3);
                    scores.put("백엔드 개발자", scores.get("백엔드 개발자") + 3);
                }
                break;
            case 8:
                if (answer == 1) {
                    scores.put("머신러닝/인공지능(AI) 개발자", scores.get("머신러닝/인공지능(AI) 개발자") + 3);
                } else if (answer == 2) {
                    scores.put("프론트엔드 개발자", scores.get("프론트엔드 개발자") + 2);
                }
                break;
            case 9:
                if (answer == 1) {
                    scores.put("프론트엔드 개발자", scores.get("프론트엔드 개발자") + 3);
                } else if (answer == 2) {
                    scores.put("머신러닝/인공지능(AI) 개발자", scores.get("머신러닝/인공지능(AI) 개발자") + 3);
                }
                break;
            case 10:
                if (answer == 1) {
                    scores.put("데브옵스 개발자", scores.get("데브옵스 개발자") + 3);
                } else if (answer == 2) {
                    scores.put("프론트엔드 개발자", scores.get("프론트엔드 개발자") + 3);
                }
                break;
            default:
                break;
        }
    }


    public ResultResponseDto calculateResult() {
        String topDeveloper = scores.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        MbtiType mbtiType = mbtiTypeRepository.findByDeveloper(topDeveloper)
                .orElse(new MbtiType(null, "미정", "결과를 결정할 수 없습니다."));
        return new ResultResponseDto(mbtiType.getDeveloper(), mbtiType.getComment());
    }

}
