package com.example.management_university.services;

import com.example.management_university.controllers.CourseGradeController;
import com.example.management_university.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseGradeService {
    private List<CourseGradeModel> courseGradeDB;
    @Autowired
    private CourseService courseService;
    private Response message = new Response();
    private int id = 0;
    public Response getMessage() {
        return message;
    }

    public CourseGradeService(){
        courseGradeDB = new ArrayList<>();
    }

    public boolean addCourseGrade(CourseGradeModel grade){
        CourseModel existingId = courseService.getCourseByid(grade.getCodeCourse());
//        message.setError("Halo");
//        return false;
        if (existingId == null){
            message.setError("Course Not Found");
            return false;
        }

        if (!validateCourseGrade(grade.getCodeCourse(), grade.getQuiz().getQuiz1(), grade.getQuiz().getQuiz2(), grade.getQuiz().getQuiz3(), grade.getQuiz().getQuiz4(), grade.getQuiz().getQuiz5(), grade)){
            return false;
        }

        List<Byte> quizScores = new ArrayList<>();
        quizScores.add(grade.getQuiz().getQuiz1());
        quizScores.add(grade.getQuiz().getQuiz2());
        quizScores.add(grade.getQuiz().getQuiz3());
        quizScores.add(grade.getQuiz().getQuiz4());
        quizScores.add(grade.getQuiz().getQuiz5());

        List<Byte> examScores = new ArrayList<>();
        quizScores.add(grade.getExam().getExam1());
        quizScores.add(grade.getExam().getExam2());

        double totalScores = 0;
        for (double quiz : quizScores){
            totalScores += quiz;
        }
        double avgQuiz = totalScores/quizScores.size();

        double totalExam = 0;
        for (double exam : examScores){
            totalExam += exam;
        }
        double avgExam = totalExam/examScores.size();

        CourseGradeModel dataGrade = new CourseGradeModel(String.valueOf(generateId()), grade.getCodeCourse(), (Quiz) quizScores, calculategrade(avgQuiz), (Exam) examScores, calculategrade(avgExam));
        courseGradeDB.add(dataGrade);
        message.setResponse("Major saved successfully ", dataGrade);
        return true;
    }

    public List<CourseGradeModel> viewAll(){
        List<CourseGradeModel> courseView = new ArrayList<>();
        for (CourseGradeModel grade : courseGradeDB){
            if (!grade.isDelete()){
                courseView.add(grade);
            }
        }
        return courseView;
    }

    public boolean validateCourseGrade(String idCourse, byte q1, byte q2, byte q3, byte q4, byte q5, CourseGradeModel grade){
        if (idCourse.isEmpty()){
            message.setError("Data Must Be Filled In");
            return false;
        }
        if (idCourse.matches("^([0-9])$")){
            message.setError("Id Course Can only input the number");
            return false;
        }
        if (grade.getQuiz() == null || q1 <0 || q2 <0|| q3 <0|| q4 <0|| q5 <0){
            message.setError("Quiz Score Must Be > 0");
            return false;
        }
        return true;
    }


    public char calculategrade(double avg){
        char gradeScore = 0;
        if (avg >= 90){
            gradeScore = 'A';
        } else if (avg <90 && avg >= 80) {
            gradeScore = 'B';
        } else if (avg <80 && avg >= 60) {
            gradeScore = 'C';
        }else if (avg <60 && avg >= 40) {
            gradeScore = 'D';
        } else if (avg <40 && avg >= 0) {
            gradeScore = 'E';
        }
        return gradeScore;
    }
    public int generateId(){
        id++;
        return id;
    }

}
