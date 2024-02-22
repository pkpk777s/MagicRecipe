package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.Cuisine.Asian;
import static model.Rating.Good;
import static org.junit.jupiter.api.Assertions.*;

public class SingleRecipeTest {
    private SingleRecipe singleRecipe;

    @BeforeEach
    void runBefore() {
        singleRecipe = new SingleRecipe("Title", new ArrayList<String>(), Good, Asian);
    }

    @Test
    void constructorTest() {
        assertEquals("Title", singleRecipe.getTitle());
        assertEquals(0, singleRecipe.getIngredientsNum());
        assertEquals(0, singleRecipe.getIngredients().size());
        assertEquals("Good", singleRecipe.getRating().toString());
        assertEquals("Asian", singleRecipe.getCuisine().toString());
    }

    @Test
    void toStringTest() {
        assertEquals("Title", singleRecipe.toString());
    }

    @Test
    void toStringTest2() {
        SingleRecipe recipe = new SingleRecipe("Title2", new ArrayList<String>(), Good, Asian);
        assertEquals("Title2", recipe.toString());
    }

}
