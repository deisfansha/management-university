package com.example.management_university.controllers;

import com.example.management_university.models.CourseModel;
import com.example.management_university.models.MajorModel;
import com.example.management_university.models.ResponseModel;
import com.example.management_university.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    private ResponseModel response;
    @PostMapping("")
    public ResponseEntity saveCourse(@RequestBody CourseModel course){
        boolean added = courseService.addCourse(course);
        if (added){
            response = new ResponseModel(courseService.getMessage().responseMessage(), courseService.getMessage().responseObject());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response = new ResponseModel(courseService.getMessage().responseMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("")
    public ResponseEntity viewData(){
        List<CourseModel> course = courseService.viewAll();
        if (course.isEmpty()){
            ResponseModel response = new ResponseModel("Data Is Empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        ResponseModel response = new ResponseModel("View Successfully", course);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity softDelete(@PathVariable("id") int id){
        boolean deleted = courseService.softDeleteMajor(String.valueOf(id));
        if (deleted){
            response = new ResponseModel(courseService.getMessage().responseMessage(), courseService.getMessage().responseObject());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response = new ResponseModel(courseService.getMessage().responseMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
