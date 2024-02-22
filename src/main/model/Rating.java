package model;

public enum Rating {
    Poor("Poor"), Fair("Fair"), Good("Good"), Very_Good("Very_Good"), Excellent("Excellent");

    private String rating;

    private Rating(String rating) {
        this.rating = rating;
    }

}

