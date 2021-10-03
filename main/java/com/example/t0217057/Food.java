package com.example.t0217057;

public class Food {
    protected String title, details;
    protected boolean isFavourite;
    public Food(String title, String details, boolean isFavourite){
        this.title = title;
        this.details = details;
        this.isFavourite = false;
    }

    //Getter

    public boolean isFavourite() {
        return isFavourite;
    }

    public String getDetails() {
        return details;
    }

    public String getTitle() {
        return title;
    }

    //Setter

    public void setDetails(String details) {
        this.details = details;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
