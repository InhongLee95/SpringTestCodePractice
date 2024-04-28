package com.jyujyu.dayonetest.service;

import com.jyujyu.dayonetest.model.StudentFail;
import com.jyujyu.dayonetest.repository.StudentFailRepository;
import com.jyujyu.dayonetest.repository.StudentPassRepository;
import com.jyujyu.dayonetest.repository.StudentScoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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


    /**
     * 평균 성적 60점이상이면 합격 미만이면 불합격 테스트 필요
     */

    @Test
    @DisplayName("성적 저장 로직 검증 / 60 점 이상인 경우")
    public void saveScoreMockTest() {

        //given : 평균점수가 60점 이상 인 경우
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);

        StudentScoreService studentScoreService = new StudentScoreService(
                studentScoreRepository,
                studentPassRepository,
                studentFailRepository
        );

        // given
        String givenStudentName = "leeinhong";
        String givenExam = "testexam";
        Integer givenKorScore = 80;
        Integer givenEnglishScore = 100;
        Integer givenMathScore = 60;


        //when : saveScore 메서드를 호출했을 때, repository.save는 1번 호출되어야 한다.
        studentScoreService.saveScore(
                givenStudentName,
                givenExam,
                givenKorScore,
                givenEnglishScore,
                givenMathScore
        );

        //then : Score 및 pass만 저장되고, fall은 저장되지 않아야 하는 검증 테스트
            //  saveScore 메서드는 점수저장메서드 이므로 항상 호출이 되어야한다.
            // 평균 60점 이상이면  StudentPass가 저장되고, 그 미만이면 StudentFail이 저장되어야 한다.
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(studentPassRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(studentFailRepository, Mockito.times(0)).save(Mockito.any());

    }

    @Test
    @DisplayName("성적 저장 로직 검증 / 60 점 미만인 경우")
    public void saveScoreMockTest2() {

        //given : 평균점수가 60점 미만 인 경우
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);

        StudentScoreService studentScoreService = new StudentScoreService(
                studentScoreRepository,
                studentPassRepository,
                studentFailRepository
        );

        // given
        String givenStudentName = "leejihong";
        String givenExam = "testexam";
        Integer givenKorScore = 40;
        Integer givenEnglishScore = 60;
        Integer givenMathScore = 60;


        //when : saveScore 메서드를 호출했을 때, repository.save는 1번 호출되어야 한다.
        studentScoreService.saveScore(
                givenStudentName,
                givenExam,
                givenKorScore,
                givenEnglishScore,
                givenMathScore
        );

        //then : Score 및 pass만 저장되고, fall은 저장되지 않아야 하는 검증 테스트
        //  saveScore 메서드는 점수저장메서드 이므로 항상 호출이 되어야한다.
        // 평균 60점 이상이면  StudentPass가 저장되고, 그 미만이면 StudentFail이 저장되어야 한다.
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(studentPassRepository, Mockito.times(0)).save(Mockito.any());
        Mockito.verify(studentFailRepository, Mockito.times(1)).save(Mockito.any());

    }


}
