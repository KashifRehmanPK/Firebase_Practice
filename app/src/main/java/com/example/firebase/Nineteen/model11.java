package com.example.firebase.Nineteen;

public class model11 {

    String contact,course,name,pimage;


    public model11() {
    }


    public model11(String contact, String course, String name, String pimage) {
        this.contact = contact;
        this.course = course;
        this.name = name;
        this.pimage = pimage;
    }


    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }
}
