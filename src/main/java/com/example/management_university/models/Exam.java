package com.example.management_university.models;

public class Exam {
    private byte exam1;
    private byte exam2;

    public Exam(byte exam1) {
        this.exam1 = exam1;
    }

    public byte getExam1() {
        return exam1;
    }

    public void setExam1(byte exam1) {
        this.exam1 = exam1;
    }

    public byte getExam2() {
        return exam2;
    }

    public void setExam2(byte exam2) {
        this.exam2 = exam2;
    }
}
