package org.naveen.springbootcrud.dao;

public class StudentErrorResponse {
    private String error;
    private String status;

    public StudentErrorResponse(String error, String status) {
        this.error = error;
        this.status = status;
    }

    public StudentErrorResponse(){

    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
