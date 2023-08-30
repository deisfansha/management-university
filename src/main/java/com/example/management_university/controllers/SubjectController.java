package com.example.management_university.controllers;

import com.example.management_university.models.MajorModel;
import com.example.management_university.models.ResponseModel;
import com.example.management_university.models.SubjectModel;
import com.example.management_university.services.Response;
import com.example.management_university.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    private ResponseModel response;

    @PostMapping("")
    public ResponseEntity saveSubject(@RequestBody SubjectModel subjects){
        boolean added = subjectService.addSubject(subjects);
        if (added){
            response = new ResponseModel(subjectService.getMessage().responseMessage(), subjectService.getMessage().responseObject());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else {
            response = new ResponseModel(subjectService.getMessage().responseMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("")
    public ResponseEntity viewAll(){
        List<SubjectModel> subject = subjectService.viewSubjects();
        if (subject.isEmpty()){
            ResponseModel response = new ResponseModel("Data Is Empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        ResponseModel response = new ResponseModel("View Successfully", subject);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity updatedSubject(@PathVariable int id, @RequestBody SubjectModel subject){
        boolean edited = subjectService.updateSubject(id, subject);
        if (edited){
            response = new ResponseModel(subjectService.getMessage().responseMessage(), subjectService.getMessage().responseObject());
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }else {
            response = new ResponseModel(subjectService.getMessage().responseMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSubject(@PathVariable int id){
        boolean deleted = subjectService.softDeleteSubject(id);
        if (deleted){
            response = new ResponseModel(subjectService.getMessage().responseMessage(), subjectService.getMessage().responseObject());
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }else {
            response = new ResponseModel(subjectService.getMessage().responseMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
