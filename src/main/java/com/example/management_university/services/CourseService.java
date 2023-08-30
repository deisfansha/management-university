package com.example.management_university.services;

import com.example.management_university.models.CollegeStudentModel;
import com.example.management_university.models.CourseModel;
import com.example.management_university.models.MajorModel;
import com.example.management_university.models.SubjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    private List<CourseModel> courseDB;
    @Autowired
    private SubjectService subjectService;
    private int id = 0;
    private Response message = new Response();
    @Autowired
    private CollegeStudentService studentService;

    public Response getMessage() {
        return message;
    }
    public CourseService(){
        courseDB = new ArrayList<>();
    }
    public boolean addCourse(CourseModel course){
        String npmStudent = course.getNpm().trim();
        String codeSubject = course.getCodeSubject();

        if (validateInputCourse(npmStudent, codeSubject)){
            return false;
        }

        CollegeStudentModel existingNpmStudent = studentService.getStudentByNpm(npmStudent);
        SubjectModel existingIdSubject = subjectService.getSubjectById(Integer.parseInt(codeSubject));
        System.out.println(existingNpmStudent);
        System.out.println(existingIdSubject);
        if (existingNpmStudent == null){
            message.setError("Student Not Found");
            return false;
        }

        if (existingIdSubject == null){
            message.setError("Subject Not Found");
            return false;
        }

        if (existingNpmStudent.getNpm().equalsIgnoreCase(npmStudent) && existingIdSubject.getIdSubject() == Integer.parseInt(codeSubject)){
            message.setError("Data Is Already Exists");
            return false;
        }


        String idCourse = String.valueOf(generateId());
        CourseModel courseData = new CourseModel("00"+idCourse, npmStudent, codeSubject);
        courseDB.add(courseData);
        message.setResponse("Add Course Successfully", courseData);
        return true;
    }

//    public boolean updateCourse(int id, CourseModel course){
//        String npmStudent = course.getNpm().trim();
//        String codeSubject = course.getCodeSubject();
//        CourseModel existingId = getCourseByid(String.valueOf(id));
//        if (existingId == null){
//            message.setError("Course Not Found");
//            return false;
//        }
//
//        if (!existingId.getNpm().equals(npmStudent) && !existingId.getCodeSubject().equalsIgnoreCase(course.getCodeSubject().trim())){
//            if (!validateInputCourse(npmStudent, codeSubject)){
//                return false;
//            }
//            existingId.setName(major.getName().trim());
//            message.setResponse("Updated Successfully", existingId);
//        }else {
//            message.setError("No Changes Detected");
//            return false;
//        }
//
//        return true;
//    }

    public boolean softDeleteMajor(String id){
        CourseModel existingId = getCourseByid(id);

        if (existingId == null){
            message.setError("Course Not Found");
            return false;
        }

        existingId.setDelete(true);
        message.setResponse("Deleted Successfully", existingId);
        return true;
    }

    public CourseModel getCourseByid(String id){
        for (CourseModel course : courseDB){
            if (course.getIdCourse().equalsIgnoreCase(id) && !course.isDelete()){
                return course;
            }
        }
        return null;
    }
    public List<CourseModel> viewAll(){
        List<CourseModel> courseView = new ArrayList<>();
        for (CourseModel course : courseDB){
            if (course.isDelete() == false){
                courseView.add(course);
            }
        }
        return courseView;
    }

    public boolean validateInputCourse(String npm, String idSubject){
        if (npm.isEmpty() || idSubject.isEmpty()){
            message.setError("Data Must Be Filled In");
            return false;
        }
        if (npm.matches("^([0-9])$") || idSubject.matches("^([0-9])$")){
            message.setError("Can only input the number");
            return false;
        }

        return true;
    }

    public int generateId(){
        id++;
        return id;
    }
}
