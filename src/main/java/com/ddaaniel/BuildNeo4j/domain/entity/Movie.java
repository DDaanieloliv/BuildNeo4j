package com.ddaaniel.BuildNeo4j.domain.entity;

public class Movie {

    private String title;

    private int released;

    public Movie(){}

    public Movie(String title, int released) {
        this.title = title;
        this.released = released;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




    public int getReleased() {
        return released;
    }

    public void setReleased(int released) {
        this.released = released;
    }
}
