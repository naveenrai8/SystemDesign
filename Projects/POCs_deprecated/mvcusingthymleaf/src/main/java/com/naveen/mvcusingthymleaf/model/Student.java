package com.naveen.mvcusingthymleaf.model;

import com.naveen.mvcusingthymleaf.customannotation.CourseCode;
import jakarta.validation.constraints.*;

import java.util.List;

public class Student {

    private String firstName;

    @NotNull(message = "is required")
    @Size(min = 2, message = "greater than 1")
    private String lastName;

    @Min(value = 1, message = "must be greater than 0")
    @Max(value = 100, message = "must be less than 100")
    @NotNull(message = "is required")
    private Integer age;

    @Pattern(regexp = "^[0-9]{6}", message = "must contains 6 digits")
    @NotNull(message = "is required")
    private String postalCode;

    private String language;

    private String country;

    @CourseCode(value = "student", message = "must starts with student")
    private String message;

    private List<String> allLanguages;

    public Student() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getAllLanguages() {
        return allLanguages;
    }

    public void setAllLanguages(List<String> allLanguages) {
        this.allLanguages = allLanguages;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
