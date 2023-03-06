package com.example.newsyllabus.syllabus;

public class User {

    String url , subjectName;

    public User(){}

    public User(String url, String subjectName) {
        this.url = url;
        this.subjectName = subjectName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
