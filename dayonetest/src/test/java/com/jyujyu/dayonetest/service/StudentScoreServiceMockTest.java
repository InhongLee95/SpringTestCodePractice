package com.jyujyu.dayonetest.service;

import com.jyujyu.dayonetest.model.StudentFail;
import com.jyujyu.dayonetest.repository.StudentFailRepository;
import com.jyujyu.dayonetest.repository.StudentPassRepository;
import com.jyujyu.dayonetest.repository.StudentScoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class StudentScoreServiceMockTest {


    /**
     * 테스트 코드안에서는 db도 없고 studentScoreRepository 라는 것도 존재하지 않는다.
     * 하지만 아래 테스트코드에서는 빨간줄이 안뜨면서 에러가 발생하지 않는다.
     * 아래 코드보면 레포지토리 클래스가 생성되지 않고도 아래 테스트할 수 있게 된다.
     */
    @Test
    @DisplayName("첫번째 Mock 테스트")
    public void firstSaveScoreMockTest() {
        StudentScoreService studentScoreService = new StudentScoreService(
                Mockito.mock(StudentScoreRepository.class),
                Mockito.mock(StudentPassRepository.class),
                Mockito.mock(StudentFailRepository.class)
        );
        String givenStudentName = "leeinhong";
        String givenExam = "testexam";
        Integer givenKorScore = 80;
        Integer givenEnglishScore = 100;
        Integer givenMathScore = 60;


        // when
        studentScoreService.saveScore(
                givenStudentName,
                givenExam,
                givenKorScore,
                givenEnglishScore,
                givenMathScore
        );

    }



}
