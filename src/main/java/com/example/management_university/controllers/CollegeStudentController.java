package com.example.management_university.controllers;

import com.example.management_university.models.CollegeStudentModel;
import com.example.management_university.models.MajorModel;
import com.example.management_university.models.ResponseModel;
import com.example.management_university.services.CollegeStudentService;

import com.example.management_university.services.Response;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class CollegeStudentController {

    @Autowired
    private CollegeStudentService studentService;
    private ResponseModel response;

    @PostMapping("")
    public ResponseEntity saveStudent(@RequestBody CollegeStudentModel students){
        boolean added = studentService.addStudent(students);
        if (added){
            response = new ResponseModel(studentService.getMessage().responseMessage(), studentService.getMessage().responseObject());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response = new ResponseModel(studentService.getMessage().responseMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity updateData(@PathVariable ("id") int id, @RequestBody CollegeStudentModel student){
        boolean edited = studentService.updateStudent(id, student);
        if (edited){
            response = new ResponseModel(studentService.getMessage().responseMessage(), student);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }else {
            response = new ResponseModel(studentService.getMessage().responseMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("")
    public ResponseEntity viewData(){
        List<CollegeStudentModel> students = studentService.view();
        if (students.isEmpty()){
            response = new ResponseModel("Data Is Empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        response = new ResponseModel("View Successfully", students);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity softDelete(@PathVariable("id") int id){
        boolean deleted = studentService.softDeleteStudent(id);
        CollegeStudentModel existing = studentService.getStudentByIdSoftDelete(id);
        if (deleted){
            response = new ResponseModel(studentService.getMessage().responseMessage(), existing);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }else {
            response = new ResponseModel(studentService.getMessage().responseMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
