package persistence;

import model.Cuisine;
import model.Rating;
import model.RecipeBook;
import model.SingleRecipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
    RecipeBook recipeBook;

    @BeforeEach
    void runBefore() {
        this.recipeBook = new RecipeBook();
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Beef");
        ingredients.add("Egg");
        recipeBook.add(new SingleRecipe("First",ingredients, Rating.Good, Cuisine.Asian));
    }

    @Test
    void InvalidFileNameTest() {
        try {
            JsonWriter writer = new JsonWriter("\nillegal name");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // Do nothing
        }
    }

    @Test
    void EmptyBookTest() {
        RecipeBook recipeBook2 = new RecipeBook();
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyJson.json");
            writer.open();
            writer.write(recipeBook2);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterEmptyJson.json");
            RecipeBook temp = reader.read();
            assertEquals(0,temp.getRecipeNum());
        } catch(IOException e) {
            fail("No Error should be thrown");
        }

    }

    @Test
    void WriterTestSingleIngredient() {
        RecipeBook recipeBook2 = new RecipeBook();
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Beef");
        recipeBook2.add(new SingleRecipe("First",ingredients, Rating.Good, Cuisine.Asian));
        try {
            JsonWriter writer = new JsonWriter("./data/WriterTestSingleIngredient.json");
            writer.open();
            writer.write(recipeBook2);
            writer.close();
            JsonReader reader = new JsonReader("./data/WriterTestSingleIngredient.json");
            RecipeBook temp = reader.read();
            assertEquals(1,temp.getRecipeNum());
            assertEquals(1,temp.getRecipes().get(0).getIngredientsNum());
        } catch(IOException e) {
            fail("No Error should be thrown");
        }

    }
    @Test
    void WriterTestMultiple() {
        try {
            JsonWriter writer = new JsonWriter("./data/WriterTestMultiple.json");
            writer.open();
            writer.write(recipeBook);
            writer.close();
            JsonReader reader = new JsonReader("./data/WriterTestMultiple.json");
            RecipeBook temp = reader.read();
            assertEquals(1,temp.getRecipeNum());
            assertEquals(2,temp.getRecipes().get(0).getIngredientsNum());
        } catch(IOException e) {
            fail("No Error should be thrown");
        }

    }
}
