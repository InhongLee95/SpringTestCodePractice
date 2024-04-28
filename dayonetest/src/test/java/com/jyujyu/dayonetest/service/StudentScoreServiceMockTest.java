package com.jyujyu.dayonetest.service;

import com.jyujyu.dayonetest.MyCalculator;
import com.jyujyu.dayonetest.controller.response.ExamPassStudentResponse;
import com.jyujyu.dayonetest.model.*;
import com.jyujyu.dayonetest.repository.StudentFailRepository;
import com.jyujyu.dayonetest.repository.StudentPassRepository;
import com.jyujyu.dayonetest.repository.StudentScoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

class StudentScoreServiceMockTest {


    private StudentScoreService studentScoreService;
    private StudentScoreRepository studentScoreRepository;
    private StudentPassRepository studentPassRepository;
    private StudentFailRepository studentFailRepository;


    @BeforeEach
    public void beforeEach() {
        studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        studentPassRepository = Mockito.mock(StudentPassRepository.class);
        studentFailRepository = Mockito.mock(StudentFailRepository.class);
        studentScoreService = new StudentScoreService(
                studentScoreRepository,
                studentPassRepository,
                studentFailRepository
        );
    }


    @Test
    @DisplayName("첫번째 Mock 테스트")
    public void firstSaveScoreMockTest() {

        // given
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
     * 성적 저장 로직 검증
     */

    @Test
    @DisplayName("성적 저장 로직 검증 / 60 점 이상인 경우")
    public void saveScoreMockTest() {

        // given
        // 제대로된 인자 값이 전달 되는지 비교할 클래스 객체 하나 생성한다.
        StudentScore expectStudentScore = StudentScoreTestDataBuilder.passed()
                .studentName("student New name")
                .mathScore(40)
                .englishScore(50)
                .korScore(30)
                .build();
        StudentPass expectStudentPass = StudentPass
                .builder()
                .studentName(expectStudentScore.getStudentName())
                .exam(expectStudentScore.getExam())
                .avgScore(
                        (new MyCalculator(0.0))
                                .add(expectStudentScore.getKorScore().doubleValue())
                                .add(expectStudentScore.getEnglishScore().doubleValue())
                                .add(expectStudentScore.getMathScore().doubleValue())
                                .divide(3.0)
                                .getResult()
                )
                .build();




        ArgumentCaptor<StudentScore> studentScoreArgumentCaptor =ArgumentCaptor.forClass(StudentScore.class);
        ArgumentCaptor<StudentPass> studentPassArgumentCaptor = ArgumentCaptor.forClass(StudentPass.class);



        //when : saveScore 메서드를 호출했을 때, repository.save는 1번 호출되어야 한다.
        studentScoreService.saveScore(
                expectStudentScore.getStudentName(),
                expectStudentScore.getExam(),
                expectStudentScore.getKorScore(),
                expectStudentScore.getEnglishScore(),
                expectStudentScore.getMathScore()
        );

        //then : Score 및 pass만 저장되고, fall은 저장되지 않아야 하는 검증 테스트
            //  saveScore 메서드는 점수저장메서드 이므로 항상 호출이 되어야한다.
            // 평균 60점 이상이면  StudentPass가 저장되고, 그 미만이면 StudentFail이 저장되어야 한다.
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(studentScoreArgumentCaptor.capture());

        StudentScore capturedStudentScore = studentScoreArgumentCaptor.getValue();
        Assertions.assertEquals(expectStudentScore.getStudentName(), capturedStudentScore.getStudentName());
        Assertions.assertEquals(expectStudentScore.getExam(), capturedStudentScore.getExam());
        Assertions.assertEquals(expectStudentScore.getKorScore(), capturedStudentScore.getKorScore());
        Assertions.assertEquals(expectStudentScore.getEnglishScore(), capturedStudentScore.getEnglishScore());
        Assertions.assertEquals(expectStudentScore.getMathScore(), capturedStudentScore.getMathScore());


        // studentPass.save 메서드 인자값 검증
        Mockito.verify(studentPassRepository, Mockito.times(1)).save(studentPassArgumentCaptor.capture());
        StudentPass capturedStudentPass = studentPassArgumentCaptor.getValue();
        Assertions.assertEquals(expectStudentPass.getStudentName(), capturedStudentPass.getStudentName());
        Assertions.assertEquals(expectStudentPass.getExam(), capturedStudentPass.getExam());
        Assertions.assertEquals(expectStudentPass.getAvgScore(), capturedStudentPass.getAvgScore());


        Mockito.verify(studentFailRepository, Mockito.times(0)).save(Mockito.any());

    }

    @Test
    @DisplayName("성적 저장 로직 검증 / 60 점 이상인 경우")
    public void saveScoreMockTestTwo() {

        // given
        // 제대로된 인자 값이 전달 되는지 비교할 클래스 객체 하나 생성한다.
        StudentScore expectStudentScore = StudentScoreFixture.passed();
        StudentPass expectStudentPass = StudentPass
                .builder()
                .studentName(expectStudentScore.getStudentName())
                .exam(expectStudentScore.getExam())
                .avgScore(
                        (new MyCalculator(0.0))
                                .add(expectStudentScore.getKorScore().doubleValue())
                                .add(expectStudentScore.getEnglishScore().doubleValue())
                                .add(expectStudentScore.getMathScore().doubleValue())
                                .divide(3.0)
                                .getResult()
                )
                .build();




        ArgumentCaptor<StudentScore> studentScoreArgumentCaptor =ArgumentCaptor.forClass(StudentScore.class);
        ArgumentCaptor<StudentPass> studentPassArgumentCaptor = ArgumentCaptor.forClass(StudentPass.class);



        //when : saveScore 메서드를 호출했을 때, repository.save는 1번 호출되어야 한다.
        studentScoreService.saveScore(
                expectStudentScore.getStudentName(),
                expectStudentScore.getExam(),
                expectStudentScore.getKorScore(),
                expectStudentScore.getEnglishScore(),
                expectStudentScore.getMathScore()
        );

        //then : Score 및 pass만 저장되고, fall은 저장되지 않아야 하는 검증 테스트
        //  saveScore 메서드는 점수저장메서드 이므로 항상 호출이 되어야한다.
        // 평균 60점 이상이면  StudentPass가 저장되고, 그 미만이면 StudentFail이 저장되어야 한다.
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(studentScoreArgumentCaptor.capture());

        StudentScore capturedStudentScore = studentScoreArgumentCaptor.getValue();
        Assertions.assertEquals(expectStudentScore.getStudentName(), capturedStudentScore.getStudentName());
        Assertions.assertEquals(expectStudentScore.getExam(), capturedStudentScore.getExam());
        Assertions.assertEquals(expectStudentScore.getKorScore(), capturedStudentScore.getKorScore());
        Assertions.assertEquals(expectStudentScore.getEnglishScore(), capturedStudentScore.getEnglishScore());
        Assertions.assertEquals(expectStudentScore.getMathScore(), capturedStudentScore.getMathScore());


        // studentPass.save 메서드 인자값 검증
        Mockito.verify(studentPassRepository, Mockito.times(1)).save(studentPassArgumentCaptor.capture());
        StudentPass capturedStudentPass = studentPassArgumentCaptor.getValue();
        Assertions.assertEquals(expectStudentPass.getStudentName(), capturedStudentPass.getStudentName());
        Assertions.assertEquals(expectStudentPass.getExam(), capturedStudentPass.getExam());
        Assertions.assertEquals(expectStudentPass.getAvgScore(), capturedStudentPass.getAvgScore());


        Mockito.verify(studentFailRepository, Mockito.times(0)).save(Mockito.any());

    }


    @Test
    @DisplayName("성적 저장 로직 검증 / 60 점 미만인 경우")
    public void saveScoreMockTest2() {


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



    @Test
    @DisplayName("합격자 명단 가져오기 검증")
    public void getPassStudentsListTest() {

        //given

        StudentPass expectStudent1 = StudentPass.builder().id(1L).studentName("leeinhong").exam("testexam").avgScore(70.0).build();
        StudentPass expectStudent2 = StudentPass.builder().id(2L).studentName("leejihong").exam("testexam").avgScore(80.0).build();
        StudentPass notExpectStudent3 = StudentPass.builder().id(3L).studentName("selmiwhaw").exam("secondeexam").avgScore(90.0).build();


        // studentPassRepository.findAll()가 호출되었을 때 아래 3개의 리스트를 반환한다는 것을 정의한 것
        Mockito.when(studentPassRepository.findAll()).thenReturn(List.of(
            expectStudent1,
            expectStudent2,
            notExpectStudent3
        ));


        String givenTestExam = "testexam";

        // when
            // stream() : 컬렉션(Collection) 객체를 스트림으로 변환하는 메서드
            // map() :  스트림의 map() 메서드를 호출합니다. map() 메서드는 스트림의 각 요소에 대해 특정 작업을 수행하여 새로운 요소로 변환하는 역할을 합니다.
            // toList : 스트림을 List 타입으로 변환하는 메서드
        var expectResponses = List.of(expectStudent1, expectStudent2)
                .stream()
                .map((pass) -> new ExamPassStudentResponse(pass.getStudentName(), pass.getAvgScore()))
                .toList();

        List<ExamPassStudentResponse> responses =  studentScoreService.getPassStudentsList(givenTestExam);

        Assertions.assertIterableEquals(expectResponses, responses);

        expectResponses.forEach((respone) -> {
            System.out.println(respone.getStudentName() + " " + respone.getAvgScore());
        });
        System.out.println("=======");
        expectResponses.forEach((respone) -> {
            System.out.println(respone.getStudentName() + " " + respone.getAvgScore());
        });


    }



}
