package com.example.m02_modul;

//kelas 'model' dipkae di foodladapt
public class Food {
    private String title;
    private String details;
    private boolean isFavorite;

    //constrc
    public Food(String title, String details, boolean isFavorite) {
        this.title = title;
        this.details = details;
        this.isFavorite = isFavorite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

}
