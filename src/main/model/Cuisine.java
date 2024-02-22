package model;

public enum Cuisine {
    Asian("Asian"), Indian("Indian"), Europe("Europe"), NorthAmerica("NorthAmerica"), SouthAmerica("SouthAmerica"),
        Oceania("Oceania");

    private String cuisine;

    private Cuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    /*
    public String getRatingString() {
        return cuisine;
    }

     */
}
