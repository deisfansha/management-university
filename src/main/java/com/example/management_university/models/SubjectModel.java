package com.example.management_university.models;

public class SubjectModel {
    private int idSubject;
    private String nameSubject;
    private int credit;

    private boolean isDelete;

    public SubjectModel(int idSubject, String nameSubject, int credits) {
        this.idSubject = idSubject;
        this.nameSubject = nameSubject;
        this.credit = credits;
        this.isDelete = false;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public int getCredits() {
        return credit;
    }

    public void setCredits(int credits) {
        this.credit = credits;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public boolean getDeleteFalse(){
        return isDelete == false;
    }

    public boolean getDeleteTrue(){
        return isDelete == true;
    }
}
