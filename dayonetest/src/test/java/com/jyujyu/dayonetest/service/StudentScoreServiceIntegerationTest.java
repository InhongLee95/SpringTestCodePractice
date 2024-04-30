package com.jyujyu.dayonetest.service;

import com.jyujyu.dayonetest.MyCalculator;
import com.jyujyu.dayonetest.dayonetest.IntegrationTest;
import com.jyujyu.dayonetest.model.StudentScoreFixture;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentScoreServiceIntegerationTest extends IntegrationTest {

    @Autowired
    private StudentScoreService studentScoreService;

    @Autowired
    private EntityManager entityManager;


    @Test
    public void savePassedStudentScoreTest() {

        // given
        var studentScore = StudentScoreFixture.passed();



        // when
        studentScoreService.saveScore(
                studentScore.getStudentName(),
                studentScore.getExam(),
                studentScore.getKorScore(),
                studentScore.getEnglishScore(),
                studentScore.getMathScore()

        );
        entityManager.flush();
        entityManager.clear();

        // then

            // 패스 학생 목록 get
        var passedStudentResponses = studentScoreService.getPassStudentsList(studentScore.getExam());

            // 통과한 학생의 수가 1명인지 검증합니다.
        Assertions.assertEquals(1, passedStudentResponses.size());

            // 첫 번째 통과한 학생의 정보를 가져와서 그에 대한 검증
        var passedStudentResponse = passedStudentResponses.get(0);


        var calculator = new MyCalculator(0.0);

            // Fixture 패턴 초기화(합격자) value  - getPassStudentsList(0) value 검증
            // 즉 초기화한 값이 pass한 student인데, 제대로 PassList에 저장되었을 때 값이 동일한지 검증
        Assertions.assertEquals(studentScore.getStudentName(), passedStudentResponse.getStudentName());
        Assertions.assertEquals(
                calculator
                        .add(studentScore.getKorScore().doubleValue())
                        .add(studentScore.getEnglishScore().doubleValue())
                        .add(studentScore.getMathScore().doubleValue())
                        .divide(3.0)
                        .getResult(), passedStudentResponse.getAvgScore());




    }





}
