package com.example.management_university.models;

public class MajorModel {
    private int idMajor;
    private String name;
    private boolean isDelete;

    public MajorModel(int idMajor, String name, boolean isDelete) {
        this.idMajor = idMajor;
        this.name = name;
        this.isDelete = isDelete;
    }

    public int getIdMajor() {
        return idMajor;
    }

    public void setIdMajor(int idMajor) {
        this.idMajor = idMajor;
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
}
