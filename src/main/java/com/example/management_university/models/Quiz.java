package com.example.management_university.models;

public class Quiz {
    private byte quiz1;
    private byte quiz2;
    private byte quiz3;
    private byte quiz4;
    private byte quiz5;

    public Quiz(byte quiz1, byte quiz2, byte quiz3, byte quiz4, byte quiz5) {
        this.quiz1 = quiz1;
        this.quiz2 = quiz2;
        this.quiz3 = quiz3;
        this.quiz4 = quiz4;
        this.quiz5 = quiz5;
    }

    public byte getQuiz1() {
        return quiz1;
    }

    public void setQuiz1(byte quiz1) {
        this.quiz1 = quiz1;
    }

    public byte getQuiz2() {
        return quiz2;
    }

    public void setQuiz2(byte quiz2) {
        this.quiz2 = quiz2;
    }

    public byte getQuiz3() {
        return quiz3;
    }

    public void setQuiz3(byte quiz3) {
        this.quiz3 = quiz3;
    }

    public byte getQuiz4() {
        return quiz4;
    }

    public void setQuiz4(byte quiz4) {
        this.quiz4 = quiz4;
    }

    public byte getQuiz5() {
        return quiz5;
    }

    public void setQuiz5(byte quiz5) {
        this.quiz5 = quiz5;
    }
}
