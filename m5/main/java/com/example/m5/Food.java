package com.example.m5;

// kelas 'model' dipake di foodladapt    lainnya View (kecuali presenter)
// filled with 'business logic'
public class Food {
    String TAG = "debug Food";
    private String title;
    private String details;
    private boolean isFavorite;

    //constrc
    public Food(String title, String details) {                                 //awalan isfavoritenya selalu false
        this.title = title;
        this.details = details;
        this.isFavorite = false;
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



/*
public class MockFood {

    public static String[] foodStringArr = { "Nasi Goreng Biasa", "Nasi Goreng Telor", "Nasi Goreng Ayam",
            "Nasi Goreng Sapi", "Nasi Goreng Seafood", "Mie Goreng Biasa", "Mie Goreng Telor", "Mie Goreng Ayam",
            "Mie Goreng Sapi", "Mie Goreng Seafood", "Baso", "Baso", "Baso", "Baso", "Baso", "Baso", "Baso", "Baso" };

    public static Food[] foodObjectArr = {
            new Food("Nasi Goreng","pake nasi"),
            new Food("Mie Goreng","pake mie"),
            new Food("Makanan 1","detail makanan 1"),
            new Food("Makanan 2","detail makanan 2"),
            new Food("Makanan 3","detail makanan 3"),
            new Food("Makanan 4","detail makanan 4"),
            new Food("Makanan 5","detail makanan 5")
    };
}
**/
