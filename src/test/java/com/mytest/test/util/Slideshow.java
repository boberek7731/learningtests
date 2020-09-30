package com.mytest.test.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class Slideshow {
//    @JsonIgnoreProperties(ignoreUnknown = true)
    private String author;
    private String date;
    private List<Slides> slides;
    private String title;

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public List<Slides> getSlides() {
        return slides;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSlides(List<Slides> slides) {
        this.slides = slides;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
