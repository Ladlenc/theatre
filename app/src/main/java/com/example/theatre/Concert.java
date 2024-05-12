package com.example.theatre;

public class Concert {
    private String id;
    private String title;
    private String info;
    private String actors;



    public Concert(String title, String info, String actors) {
        this.title = title;
        this.info = info;
        this.actors = actors;

    }
    public Concert(){}

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public String getActors() {
        return actors;
    }



    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }


}
