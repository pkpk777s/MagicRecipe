package persistence;

import model.Cuisine;
import model.Rating;
import model.RecipeBook;
import model.SingleRecipe;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static model.Cuisine.Asian;
import static model.Rating.Good;

import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {


    @Test
    void constructorTest() {
        JsonReader reader = new JsonReader("./data/testFile.json");
        try {
            RecipeBook recipeBook = reader.read();
        } catch (IOException e) {
            fail("IO Error");
        }
    }

    @Test
    void FileNotExistTest() {
        try {
            JsonReader reader = new JsonReader("./data/Noooooooo.json");
            RecipeBook recipeBook = reader.read();
            fail("No Exception thrown");
        } catch (IOException e) {
            // Do nothing
        } catch (JSONException e){
            // Do nothing
        }
    }

    @Test
    void InvalidFileNameTest() {
        try {
            JsonReader reader = new JsonReader("./data/\ndashio");
            RecipeBook recipeBook = reader.read();
            fail("No Exception thrown");
        } catch (IOException e) {
            // Do nothing
        } catch (JSONException e){
            // Do nothing
        }
    }

    @Test
    void EmptyFileTest() {
        try {
            JsonReader reader = new JsonReader("./data/EmptyFile.json");
            RecipeBook recipeBook = reader.read();
            fail("No Exception thrown");
        } catch (IOException e) {
            // Do nothing
        } catch (JSONException e) {
            // Do nothing
        }
    }

    @Test
    void FileTest() {
        try {
            JsonReader reader = new JsonReader("./data/recipeBook.json");
            RecipeBook recipeBook = reader.read();
        } catch (IOException e) {
            fail("UnExpected IOException thrown");
        }
    }
}
