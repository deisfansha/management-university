package com.example.management_university.models;

public class CourseGradeModel {
    private String idGrade;
    private String codeCourse;
    private Quiz quiz;
    private char gradeQuiz;
    private Exam exam;
    private char gradeExam;
    private boolean isDelete;

    public CourseGradeModel(String idGrade, String codeCourse, Quiz quiz, char gradeQuiz, Exam exam, char gradeExam) {
        this.idGrade = idGrade;
        this.codeCourse = codeCourse;
        this.quiz = quiz;
        this.gradeQuiz = gradeQuiz;
        this.exam = exam;
        this.gradeExam = gradeExam;
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

    public char getGradeQuiz() {
        return gradeQuiz;
    }

    public void setGradeQuiz(char gradeQuiz) {
        this.gradeQuiz = gradeQuiz;
    }

    public char getGradeExam() {
        return gradeExam;
    }

    public void setGradeExam(char gradeExam) {
        this.gradeExam = gradeExam;
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
