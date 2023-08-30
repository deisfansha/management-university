package com.example.management_university.models;

public class ResponseModel {
    private String message;
    private Object data;

    public ResponseModel(String message, Object data) {
        this.message = message;
        this.data = data;
    }
    public ResponseModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
