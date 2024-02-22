package persistence;

import model.Cuisine;
import model.Rating;
import model.RecipeBook;
import model.SingleRecipe;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JsonReader {
    private String filePath;

    /*
     *EFFECTS: construct a Json reader and input the file location
     */
    public JsonReader(String filePath) {
        this.filePath = filePath;
    }

    /*
     *EFFECTS: read json file and return a RecipeBook object
     */
    public RecipeBook read() throws IOException {
        String json = readFile();
        JSONObject jsonObject = new JSONObject(json);
        return parseJson(jsonObject);
    }

    /*
     *EFFECTS: read file from file location and return it as string
     */
    private String readFile() {
        StringBuilder stringBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(this.filePath),StandardCharsets.UTF_8)) {
            stream.forEach(i -> stringBuilder.append(i));
        } catch (Exception e) {
            //do nothing
        }
        return stringBuilder.toString();
    }

    /*
     *EFFECTS: parse from json object to RecipeBook object and return
     */
    private RecipeBook parseJson(JSONObject jsonObject) {
        RecipeBook recipeBook = new RecipeBook();
        JSONArray jsonArray = jsonObject.getJSONArray("Recipes");
        for (Object next : jsonArray) {
            JSONObject nextRecipe = (JSONObject) next;
            addToBook(recipeBook, nextRecipe);
        }
        return recipeBook;
    }

    /*
     *MODIFIES: recipebook
     *EFFECTS: add data into the RecipeBook Object
     */

    private void addToBook(RecipeBook recipeBook, JSONObject jsonObject) {
        String title = jsonObject.getString("Title: ");
        List<String> ingredients = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("Ingredients:");
        for (Object next : jsonArray) {
            //JSONObject nextRecipe = (JSONObject) next;
            ingredients.add(next.toString());
        }
        Rating rating = Rating.valueOf(jsonObject.getString("Rating:"));
        Cuisine cuisine = Cuisine.valueOf(jsonObject.getString("Cuisine:"));
        recipeBook.add(new SingleRecipe(title, ingredients, rating, cuisine));
    }

}
