package com.miaoxing.web.dto;

public class CatQueryRequest {

    private String status;
    private String gender;
    private String ageKeyword;
    private String nameKeyword;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAgeKeyword() {
        return ageKeyword;
    }

    public void setAgeKeyword(String ageKeyword) {
        this.ageKeyword = ageKeyword;
    }

    public String getNameKeyword() {
        return nameKeyword;
    }

    public void setNameKeyword(String nameKeyword) {
        this.nameKeyword = nameKeyword;
    }
}
