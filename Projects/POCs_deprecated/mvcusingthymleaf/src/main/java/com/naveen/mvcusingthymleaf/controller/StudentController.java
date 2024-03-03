package com.naveen.mvcusingthymleaf.controller;

import com.naveen.mvcusingthymleaf.model.Student;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Value("${countries}")
    private List<String> countries;

    @Value("${languages}")
    private List<String> languages;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showStudentForm")
    public String showStudentForm(Model model){
        model.addAttribute("student", new Student());
        model.addAttribute("countries", countries);
        model.addAttribute("languages", languages);
        return "showstudent-form";
    }

    @PostMapping("/studentform")
    public String studentForm(
            @Valid @ModelAttribute("student") Student student,
            BindingResult bindingResult, Model model){

        /*
        If incorrect value is passed in age field,
        using messages.properties, we are overriding the Spring default error and provide our own custom error messages.

        Field error in object 'student' on field 'age': rejected value [sgadhadsg]; codes [typeMismatch.student.age,typeMismatch.age,typeMismatch.java.lang.Integer,typeMismatch]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [student.age,age]; arguments []; default message [age]]; default message [Failed to convert property value of type 'java.lang.String' to required type 'java.lang.Integer' for property 'age'; For input string: "sgadhadsg"]
         */
        System.out.println("Binding results: " + bindingResult.toString());

        if (bindingResult.hasErrors()){
            model.addAttribute("countries", countries);
            model.addAttribute("languages", languages);
            return "showstudent-form";
        }
        student.setFirstName(student.getFirstName().toUpperCase());
        student.setLastName(student.getLastName().toUpperCase());
        return "showstudentinfo";
    }
}
