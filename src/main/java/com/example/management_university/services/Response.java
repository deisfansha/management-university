package com.example.management_university.services;

import com.example.management_university.models.MajorModel;
import com.example.management_university.models.ResponseModel;
public class Response {
    private ResponseModel response;

    public void setResponse(String message, Object data){
        response = new ResponseModel(message, data);
    }

    public void setError(String message){
       response = new ResponseModel(message);
    }

    public String responseMessage(){
        if (response!= null){
            return response.getMessage();
        }
        return "";
    }

    public Object responseObject(){
        if (response!= null){
            return response.getData();
        }
        return "";
    }
}
