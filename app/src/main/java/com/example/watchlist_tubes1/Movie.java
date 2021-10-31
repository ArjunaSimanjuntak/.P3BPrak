package com.example.watchlist_tubes1;

import androidx.annotation.NonNull;

public class Movie {
    protected String title, synopsis, review;
    protected boolean status;
    protected int star;

    protected DatabaseHelper myDB;                                                                 

    public Movie(String title, String synopsis) {
        this.title = title;
        this.synopsis=synopsis;
        this.review = "";
        this.status = false;
        this.star= 0;
    }

    // tidak dipake
    @NonNull
    @Override
    public String toString() {
        return "MovieModel{" +
                "title=" + title +
                ", synopsis=" + synopsis +
                ", review=" + review +
                ", status=" + status +
                ", star=" + star +
                '}';                                                                                // "}"
    }

    public String getTitle() {
        return title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getReview() {
        return review;
    }

    public boolean getStatus() {
        return status;
    }                                                                                               // kynya mending getStatus

    public int getStar() {
        return star;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
