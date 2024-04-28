package com.jyujyu.dayonetest.model;

public class StudentScoreTestDataBuilder {

    public static StudentScore.StudentScoreBuilder passed() {
        return StudentScore
                .builder()
                .korScore(80)
                .englishScore(100)
                .mathScore(90)
                .studentName("defaultName")
                .exam("delfaultExam");
    }

    public static StudentScore.StudentScoreBuilder failed() {
        return StudentScore
                .builder()
                .korScore(50)
                .englishScore(400)
                .mathScore(30)
                .studentName("defaultName")
                .exam("delfaultExam");

    }

}
