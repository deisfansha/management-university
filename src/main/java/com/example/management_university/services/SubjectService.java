package com.example.management_university.services;
import com.example.management_university.models.SubjectModel;
import org.springframework.stereotype.Service;
//import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SubjectService {
    private List<SubjectModel> subjectDB;
//    private int id;
    private Response message = new Response();

    public SubjectService(){
        subjectDB = new ArrayList<>();
    }

    public Response getMessage() {
        return message;
    }
    public boolean addSubject(SubjectModel subject){
        String name = subject.getNameSubject().trim();

        if (getSubjectByName(name) != null){
            message.setError("Subject already exists");
            return false;
        }

        if (!validateInputSubject(subject.getCredits(), name)){
            return false;
        }
        SubjectModel dataSubject = new SubjectModel(subject.getIdSubject(), name, subject.getCredits());
        subjectDB.add(dataSubject);
        message.setResponse("Saved Subject Successfully", dataSubject);
        return true;
    }

    public boolean updateSubject(int id, SubjectModel subjects){
        SubjectModel existingId = getSubjectById(id);
        String credit = String.valueOf(subjects.getCredits());
        if (existingId == null){
            message.setError("Subject Not Found");
            return false;
        }

        if (!existingId.getNameSubject().equals(subjects.getNameSubject().trim()) || !String.valueOf(existingId.getCredits()).equalsIgnoreCase(credit)){
            if (!validateInputSubject(existingId.getCredits(), existingId.getNameSubject())){
                return false;
            }
            existingId.setNameSubject(subjects.getNameSubject().trim());
            existingId.setCredits(Integer.parseInt(credit));
            message.setResponse("Updated Successfully", existingId);
        }else {
            message.setError("No Changes Detected");
            return false;
        }
        return true;
    }

    public List<SubjectModel> viewSubjects(){
        List<SubjectModel> subjectData = new ArrayList<>();
        for (SubjectModel subject : subjectDB){
            if (subject.getDeleteFalse()){
                subjectData.add(subject);
            }
        }
        return subjectData;
    }

    public boolean softDeleteSubject(int id){
        SubjectModel existingId = getSubjectById(id);

        if (existingId == null){
            message.setError("Subject Not Found");
            return false;
        }

        existingId.setDelete(true);
        message.setResponse("Deleted Successfully", existingId);
        return true;
    }

    public SubjectModel getSubjectByIdSoftDelete(int id){
        for(SubjectModel subjectData : subjectDB){
            if (subjectData.getIdSubject() == id && subjectData.getDeleteTrue()){
                return subjectData;
            }
        }
        return null;
    }

    public void seedData(){
        SubjectModel subjects = new SubjectModel(1, "Bahasa Pemrograman", 20);
        subjectDB.add(subjects);
    }
    public SubjectModel getSubjectByName(String name){
        for(SubjectModel subjectData : subjectDB){
            if (subjectData.getNameSubject().equalsIgnoreCase(name) && subjectData.getDeleteFalse()){
                return subjectData;
            }
        }
        return null;
    }

    public SubjectModel getSubjectById(int id){
        for(SubjectModel subjectData : subjectDB){
            if (subjectData.getIdSubject() == id && subjectData.getDeleteFalse()){
                return subjectData;
            }
        }
        return null;
    }
    public boolean validateInputSubject(int credits, String name){
        if (credits < 1 || name.isEmpty()) {
            message.setError("Data Must Be Filled In");
            return false;
        }

        return true;
    }

//    public int generateId(){
//        id++;
//        return id;
//    }

}
