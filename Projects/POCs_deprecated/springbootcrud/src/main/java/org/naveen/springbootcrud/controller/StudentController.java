package org.naveen.springbootcrud.controller;

import org.naveen.springbootcrud.dao.StudentDao;
import org.naveen.springbootcrud.dao.StudentErrorResponse;
import org.naveen.springbootcrud.exceptions.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class StudentController {

    List<StudentDao> students = new ArrayList<>();
    public StudentController(){
        students.add(new StudentDao("Naveen", 36));
        students.add(new StudentDao("Jyoti", 32));
        students.add(new StudentDao("Alisha", 4));
    }
    @GetMapping("students/{studentId}")
    public String getWeather(@PathVariable int studentId){
        if (studentId > students.size()-1 || studentId < 0){
            throw new StudentNotFoundException("Student id not exists " + studentId);
        }

        return students.get(studentId).toString();
    }

//    /*
//    method parameter catches any exception which is super of exception passed as parameter
//     */
//    @ExceptionHandler
//    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException ex){
//        return new ResponseEntity<>(new StudentErrorResponse(ex.toString(), HttpStatus.NOT_FOUND.toString()),
//                HttpStatus.FOUND);
//    }
//
//    /*
//    This acts as catch all exception for this controller
//     */
//    @ExceptionHandler
//    public ResponseEntity<StudentErrorResponse> handleException(Exception ex){
//        return new ResponseEntity<>(new StudentErrorResponse(ex.toString(), HttpStatus.NOT_FOUND.toString()),
//                HttpStatus.FOUND);
//    }
}
