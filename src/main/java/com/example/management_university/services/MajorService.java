package com.example.management_university.services;

import com.example.management_university.models.MajorModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MajorService {
    private List<MajorModel> majorDB;
    private int id = 0;
    private Response message = new Response();

    public MajorService(){
        majorDB= new ArrayList<>();
    }

    public Response getMessage() {
        return message;
    }

    public boolean addMajor(MajorModel major){
        String nameMajor = major.getName().trim();

        if (getMajorByName(nameMajor) != null){
            message.setError("Name is already exists");
            return false;
        }

        if (!validateInput(nameMajor)){
            return false;
        }

        MajorModel dataMajor = new MajorModel(generateId(), nameMajor, false);
        majorDB.add(dataMajor);
        message.setResponse("Major saved successfully ", dataMajor);
        return true;
    }

    public boolean updateMajor(int id, MajorModel major){
        MajorModel existingId = getMajorById(id);


        if (existingId == null){
            message.setError("Major Not Found");
            return false;
        }

        if (!existingId.getName().equals(major.getName().trim())){
            if (!validateInput(existingId.getName())){
                return false;
            }
            existingId.setName(major.getName().trim());
            message.setResponse("Updated Successfully", existingId);
        }else {
            message.setError("No Changes Detected");
            return false;
        }

        return true;
    }

    public boolean softDeleteMajor(int id){
        MajorModel existingId = getMajorById(id);

        if (existingId == null){
            message.setError("Major Not Found");
            return false;
        }

        existingId.setDelete(true);
        message.setResponse("Deleted Successfully", existingId);
        return true;
    }

    public List<MajorModel> viewAll(){
        seedData();
        List<MajorModel> majorView = new ArrayList<>();
        for (MajorModel major :majorDB){
            if (major.isDelete() == false){
                majorView.add(major);
            }
        }
        return majorView;
    }
    public MajorModel getMajorByName(String nameMajor){
        for (MajorModel major : majorDB){
            if (major.getName().equalsIgnoreCase(nameMajor)&& !major.isDelete()){
                return major;
            }
        }
        return null;
    }

    public MajorModel getMajorById(int id){
        for (MajorModel major : majorDB){
            if (major.getIdMajor() == id && !major.isDelete()){
                return major;
            }
        }
        return null;
    }

    public MajorModel getMajorByIdSoftDelete(int id){
        for (MajorModel major : majorDB){
            if (major.getIdMajor() == id && major.isDelete()){
                return major;
            }
        }
        return null;
    }

    public boolean validateInput(String nameMajor){
        if (nameMajor.isEmpty()){
            message.setError("Data Must Be Filled In");
            return false;
        }
        if (!nameMajor.matches("^[a-zA-Z -]*$")){
            message.setError("Can only input the alphabet");
            return false;
        }
        return true;
    }

    public void seedData(){
        if (majorDB.size()==0){
            MajorModel major = new MajorModel(11, "Teknik Informatika", false);
            majorDB.add(major);
        }
    }

    public int generateId(){
        id+=11;
        return id;
    }

//    public void setResponse(String message, Object data){
//        response = new ResponseModel(message, data);
//    }
//
//    public void setError(String message){
//       response = new ResponseModel(message);
//    }
//
//    public String responseMessage(){
//        if (response!= null){
//            return response.getMessage();
//        }
//        return "";
//    }

}
