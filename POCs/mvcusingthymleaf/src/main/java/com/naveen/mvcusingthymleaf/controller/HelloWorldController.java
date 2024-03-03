package com.naveen.mvcusingthymleaf.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    // Request mapping handles all HTTP methods (GET, POST, PUT, DELETE etc).
    @RequestMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }

    @RequestMapping("/processForm")
    public String processForm(){
        return "processForm";
    }

    // need a controller method to read form data and add data to the model
    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request, Model model){
        model.addAttribute("message" , request.getParameter("studentName").toUpperCase());
        return "processForm";
    }

    // need a controller method to read form data and add data to the model
    // @Requestparam can go without mentioning student name inside it. We can pass param key here in case variable
    // name and param name is different
    @RequestMapping("/processFormVersionThree")
    public String letsShoutDudeUsingParam(@RequestParam("studentName") String studentName, Model model){
        model.addAttribute("message" , studentName.toUpperCase() + " by reading request Param");
        return "processForm";
    }

    // need a controller method to read form data and add data to the model
    // @Requestparam can go without mentioning student name inside it. We can pass param key here in case variable
    // name and param name is different
    @GetMapping("/processFormVersionFour")
    public String letsShoutDudeGetMapping(@RequestParam("studentName") String studentName, Model model){
        model.addAttribute("message" , studentName.toUpperCase() + " by calling GET");
        return "processForm";
    }

    // need a controller method to read form data and add data to the model
    // @Requestparam can go without mentioning student name inside it. We can pass param key here in case variable
    // name and param name is different
    @PostMapping("/processFormVersionFive")
    public String letsShoutDudePostMapping(@RequestParam("studentName") String studentName, Model model){
        model.addAttribute("message" , studentName.toUpperCase() + " by calling POST");
        return "processForm";
    }
}
