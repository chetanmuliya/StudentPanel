package com.example.chetanmuliya.studentpanel.model;

/**
 * Created by chetanmuliya on 12/19/2017.
 */

public class Student {

    private String profilePic_Url;
    private String name;
    private String fullname;
    private String username;
    private String address;
    private String mobile_no;
    private String school;
    private String emailid;
    private String joiningId;
    private String DOB;

    public Student() {
    }

    public Student(String name, String fullname, String username, String address, String mobile_no, String school, String emailid, String joiningId , String profilePic_Url) {
        this.name = name;
        this.fullname = fullname;
        this.username = username;
        this.address = address;
        this.mobile_no = mobile_no;
        this.school = school;
        this.emailid = emailid;
        this.joiningId = joiningId;
        this.profilePic_Url=profilePic_Url;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getProfilePic_Url() {
        return profilePic_Url;
    }

    public void setProfilePic_Url(String profilePic_Url) {
        this.profilePic_Url = profilePic_Url;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getJoiningId() {
        return joiningId;
    }

    public void setJoiningId(String joiningId) {
        this.joiningId = joiningId;
    }
}
