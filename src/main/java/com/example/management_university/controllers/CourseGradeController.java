package com.example.management_university.controllers;

import com.example.management_university.models.CollegeStudentModel;
import com.example.management_university.models.CourseGradeModel;
import com.example.management_university.models.ResponseModel;
import com.example.management_university.services.CourseGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course-grades")
public class CourseGradeController {
    @Autowired
    private CourseGradeService courseGradeService;
    private ResponseModel response;
    @PostMapping("")
    public ResponseEntity saveCourseGrade(@RequestBody CourseGradeModel grade){
        boolean added = courseGradeService.addCourseGrade(grade);
        if (added){
            response = new ResponseModel(courseGradeService.getMessage().responseMessage(), courseGradeService.getMessage().responseObject());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response = new ResponseModel(courseGradeService.getMessage().responseMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

    }

}
