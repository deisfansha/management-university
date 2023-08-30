package com.example.management_university.models;

public class CollegeStudentModel {
    private String id;
    private String npm;
    private String codeMajor;
    private String name;
    private String gender;
    private boolean isDelete;

    public CollegeStudentModel(String id, String codeMajor, String npm, String name, String gender) {
        this.id = id;
        this.codeMajor = codeMajor;
        this.npm = npm;
        this.name = name;
        this.gender = gender;
        this.isDelete = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCodeMajor() {
        return codeMajor;
    }

    public void setCodeMajor(String codeMajor) {
        this.codeMajor = codeMajor;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }
}
