package com.example.management_university.models;

public class CourseGradeModel {
    private String idGrade;
    private String codeCourse;
    private Quiz quiz;
    private Exam exam;
    private char gradeScore;
    private boolean isDelete;

    public CourseGradeModel(String idGrade, String codeCourse, Quiz quiz, Exam exam, char gradeScore) {
        this.idGrade = idGrade;
        this.codeCourse = codeCourse;
        this.quiz = quiz;
        this.exam = exam;
        this.gradeScore = gradeScore;
        this.isDelete = false;
    }

    public String getCodeCourse() {
        return codeCourse;
    }

    public void setCodeCourse(String codeCourse) {
        this.codeCourse = codeCourse;
    }

    public String getIdGrade() {
        return idGrade;
    }

    public void setIdGrade(String idGrade) {
        this.idGrade = idGrade;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public char getGradeScore() {
        return gradeScore;
    }

    public void setGradeScore(char gradeScore) {
        this.gradeScore = gradeScore;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
