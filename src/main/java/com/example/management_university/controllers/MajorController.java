package com.example.management_university.controllers;

import com.example.management_university.models.MajorModel;
import com.example.management_university.models.ResponseModel;
import com.example.management_university.services.MajorService;
import com.example.management_university.services.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/majors")
public class MajorController {
    @Autowired
    private MajorService majorService;
    private ResponseModel response;

    @PostMapping("")
    public ResponseEntity saveMajor(@RequestBody MajorModel major){
        boolean added = majorService.addMajor(major);

        if (added){
            response = new ResponseModel(majorService.getMessage().responseMessage(), majorService.getMessage().responseObject());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response = new ResponseModel(majorService.getMessage().responseMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity editMajor(@PathVariable("id") int id, @RequestBody MajorModel major){
        boolean edited = majorService.updateMajor(id, major);
        if (edited){
            response = new ResponseModel(majorService.getMessage().responseMessage(), majorService.getMessage().responseObject());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response = new ResponseModel(majorService.getMessage().responseMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("")
    public ResponseEntity viewData(){
        List<MajorModel> major = majorService.viewAll();
        if (major.isEmpty()){
            ResponseModel response = new ResponseModel("Data Is Empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        ResponseModel response = new ResponseModel("View Successfully", major);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity softDelete(@PathVariable("id") int id){
        boolean deleted = majorService.softDeleteMajor(id);
        MajorModel existing = majorService.getMajorByIdSoftDelete(id);
        if (deleted){
            response = new ResponseModel(majorService.getMessage().responseMessage(), majorService.getMessage().responseObject());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response = new ResponseModel(majorService.getMessage().responseMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
