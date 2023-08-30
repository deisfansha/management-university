package com.example.management_university.models;

public class CourseModel {

    private String idCourse;
    private String npm;
    private String codeSubject;
    private boolean isDelete;

    public CourseModel(String idCourse, String npm, String codeSubject) {
        this.idCourse = idCourse;
        this.npm = npm;
        this.codeSubject = codeSubject;
        this.isDelete = false;
    }

    public String getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(String idCourse) {
        this.idCourse = idCourse;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getCodeSubject() {
        return codeSubject;
    }

    public void setCodeSubject(String codeSubject) {
        this.codeSubject = codeSubject;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
