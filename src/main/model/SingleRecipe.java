package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SingleRecipe {
    private String title;
    private List<String> ingredients;
    private Rating rating;
    private Cuisine cuisine;

    /*
     * Constructs a single recipe.
     * REQUIRES: ingredients number > 0,
     * title and ingredients is not an empty String and could only contain number, alphabet and space
     * ,and they need to have the first character uppercase.
     * EFFECTS: create a new Singlerecipe object and set the title, all ingredients, rating and cuisine.
     */

    public SingleRecipe(String title, List<String> ingredients, Rating rating, Cuisine cuisine) {
        this.title = title;
        this.ingredients = ingredients;
        this.rating = rating;
        this.cuisine = cuisine;
    }

    public String getTitle() {
        return this.title;
    }
    //Title getter return the title

    public List<String> getIngredients() {
        return this.ingredients;
    }
    //Ingredients getter return the List of ingredients

    public Rating getRating() {
        return this.rating;
    }
    //Rating getter return the rating

    public Cuisine getCuisine() {
        return this.cuisine;
    }
    //Cuisine getter return the cuisine;

    public int getIngredientsNum() {
        return this.ingredients.size();
    }

    /*
     * EFFECTS: returns as json object
     */
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Title: ", this.title);
        json.put("Ingredients:", listToJson());
        json.put("Rating:", this.rating);
        json.put("Cuisine:", this.cuisine);
        return json;
    }

    /*
     * EFFECTS: make list to JSON array and return
     */
    private JSONArray listToJson() {
        JSONArray jsonArray = new JSONArray();
        for (String next : this.ingredients) {
            jsonArray.put(next);
        }
        return jsonArray;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
