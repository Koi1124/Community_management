package model;

public class User {
    private String stuNum;
    private String stuName;
    private String stuSchool;
    private String stuBirth;
    private String stuSex;
    private String stuProfess;
    private String stuNumber;
    private String uName;
    private String uPassword;
    private String stuSrc;

    public String getStuBirth() { return stuBirth; }

    public String getStuNumber() { return stuNumber; }

    public String getStuName() {
        return stuName;
    }

    public String getStuNum() {
        return stuNum;
    }

    public String getStuProfess() {
        return stuProfess;
    }

    public String getStuSchool() {
        return stuSchool;
    }

    public String getStuSex() {
        return stuSex;
    }

    public String getuName() {
        return uName;
    }

    public String getStuSrc() {
        return stuSrc;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public void setStuProfess(String stuProfess) {
        this.stuProfess = stuProfess;
    }

    public void setStuSchool(String stuSchool) {
        this.stuSchool = stuSchool;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public void setStuBirth(String stuBirth) {
        this.stuBirth = stuBirth;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public void setStuSrc(String stuSrc) {
        this.stuSrc = stuSrc;
    }

}
