package com.example.videomeeting.models;

public class putPDF {

    public String name;
    public String url;
    public String courseCode;
    public String noteCode;
    public String notePassword;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getNoteCode() {
        return noteCode;
    }

    public void setNoteCode(String noteCode) {
        this.noteCode = noteCode;
    }

    public String getNotePassword() {
        return notePassword;
    }

    public void setNotePassword(String notePassword) {
        this.notePassword = notePassword;
    }



    public putPDF(){
    }

    public putPDF(String name,String url,String courseCode,String noteCode,String notePassword){
        this.name = name;
        this.url = url;
        this.courseCode = courseCode;
        this.noteCode = noteCode;
        this.notePassword = notePassword;

    }
    public putPDF(String name,String url){
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
