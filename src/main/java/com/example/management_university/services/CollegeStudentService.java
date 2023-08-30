package com.example.management_university.services;

import com.example.management_university.models.CollegeStudentModel;
import com.example.management_university.models.MajorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CollegeStudentService {
    @Autowired
    private MajorService majorService;
    private List<CollegeStudentModel> studentDB;
    private int lastStudent = 0;
    private Response message = new Response();

    public CollegeStudentService() {
        studentDB = new ArrayList<>();
    }

    public Response getMessage() {
        return message;
    }

    public boolean addStudent(CollegeStudentModel students){
        String nameStudent = students.getName().trim();
        String genderStudent = students.getGender().trim();
        int codeMajor = Integer.parseInt(students.getCodeMajor());

        if (getStudentByName(nameStudent) != null && getStudentByName(String.valueOf(codeMajor)) != null){
            message.setError("Data Is Already Exists");
            return false;
        }

        if (!validateInputStudent(nameStudent, genderStudent, String.valueOf(codeMajor))){
            return false;
        }

        MajorModel existingId = majorService.getMajorById(codeMajor);


        if (existingId == null){
            message.setError("Code major or Id Major Not Found");
            return false;
        }

        if (getStudentByNpm(nameStudent) != null && existingId.getName().equalsIgnoreCase(students.getCodeMajor())){
            message.setError("Data is Already Exists");
            return false;
        }

        String npmStudent = students.getCodeMajor()+"000" + generateId();
        CollegeStudentModel studentData = new CollegeStudentModel(String.valueOf(generateId()), String.valueOf(codeMajor),npmStudent, nameStudent,genderStudent);
        studentDB.add(studentData);
        message.setResponse("Add Student Successfully", studentData);
        return true;
    }

    public boolean updateStudent(int id, CollegeStudentModel student){
        CollegeStudentModel existingId = getStudentById(id);
        String nameStudent = student.getName().trim();

        if (existingId == null){
            message.setError("Student Not Found");
            return false;
        }

        if (!existingId.getName().equals(nameStudent)){
            if (nameStudent.isEmpty()){
                message.setError("All data must be filled in");
                return false;
            }

            if (!nameStudent.matches("^[a-zA-Z -]*$")){
                message.setError("Can only input the alphabet");
                return false;
            }
            existingId.setName(student.getName().trim());
            message.setResponse("Updated Student Successfully", existingId);
        }else {
            message.setError("No Changes Detected");
            return false;
        }

        return true;
    }

    public boolean softDeleteStudent(int id){
        CollegeStudentModel existingId = getStudentById(id);

        if (existingId == null){
            message.setError("Student Not Found");
            return false;
        }

        existingId.setDelete(true);
        message.setResponse("Deleted Successfully", existingId);
        return true;
    }

    public List<CollegeStudentModel> view(){
        List<CollegeStudentModel> studentList = new ArrayList<>();
        for (CollegeStudentModel students :studentDB){
            if (!students.isDelete()){
                studentList.add(students);
            }
        }
        return studentList;
    }
    public CollegeStudentModel getStudentByIdSoftDelete(int id){
        for (CollegeStudentModel student : studentDB){
            if (student.getId().equalsIgnoreCase(String.valueOf(id)) && student.isDelete()){
                return student;
            }
        }
        return null;
    }

    public CollegeStudentModel getStudentById(int id){
        for (CollegeStudentModel student : studentDB){
            if (student.getId().equalsIgnoreCase(String.valueOf(id)) && !student.isDelete()){
                return student;
            }
        }
        return null;
    }

    public CollegeStudentModel getStudentByCodeMajor(int codeMajor){
        for (CollegeStudentModel student : studentDB){
            if (student.getCodeMajor().equalsIgnoreCase(String.valueOf(codeMajor)) && !student.isDelete()){
                return student;
            }
        }
        return null;
    }

    public CollegeStudentModel getStudentByNpm(String npm){
        for (CollegeStudentModel student : studentDB){
            if (student.getNpm().equalsIgnoreCase(npm) && !student.isDelete()){
                return student;
            }
        }
        return null;
    }

    public CollegeStudentModel getStudentByName(String name){
        for (CollegeStudentModel student : studentDB){
            if (student.getName().equalsIgnoreCase(name) && student.isDelete()){
                return student;
            }
        }
        return null;
    }

    public boolean validateInputStudent(String nameStudent, String genderStudent, String codeMajor){
        // Validasi nama harus berupa alfabet
        if (nameStudent.isEmpty() || genderStudent.isEmpty() || codeMajor.isEmpty()){
            message.setError("All data must be filled in");
            return false;
        }

        if (!nameStudent.matches("^[a-zA-Z -]*$") || !genderStudent.matches("^[a-zA-Z -]*$")){
            message.setError("Can only input the alphabet");
            return false;
        }

//        if (!genderStudent.equalsIgnoreCase("Laki-Laki")||!genderStudent.equalsIgnoreCase("Perempuan")){
//            message.setError("There only 2 Gender");
//            return false;
//        }
        return true;
    }
    public int generateId(){
        lastStudent+= 1;
        return lastStudent;
    }

}
