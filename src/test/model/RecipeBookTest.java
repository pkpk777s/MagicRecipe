package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static java.util.Objects.isNull;
import static model.Rating.*;
import static model.Cuisine.*;
import static org.junit.jupiter.api.Assertions.*;

public class RecipeBookTest {
    private RecipeBook recipeBook;

    @BeforeEach
    void runBefore(){
        recipeBook = new RecipeBook();
    }

    @Test
    void constructorTest(){
        assertEquals(0,recipeBook.getRecipes().size()); // test simply view all recipes numbers
        assertEquals(0,recipeBook.getRecipeNum()); // test all
        assertEquals(0,recipeBook.getMyIngredientsNum());
    }

    @Test
    void addTest(){
        recipeBook.add(new SingleRecipe("Title", new ArrayList<String>(), Good,Asian)); //  adding the recipe
        assertEquals(1,recipeBook.getRecipes().size());
        assertEquals(1,recipeBook.getRecipeNum());
    }

    @Test
    void addMultiTest(){
        recipeBook.add(new SingleRecipe("Title", new ArrayList<String>(), Good, NorthAmerica));
        recipeBook.add(new SingleRecipe("Title2", new ArrayList<String>(), Poor, Asian));
        recipeBook.add(new SingleRecipe("Title3", new ArrayList<String>(), Good, SouthAmerica));
        assertEquals(3,recipeBook.getRecipes().size());
        assertEquals(3,recipeBook.getRecipeNum());
    }

    @Test
    void removeTest(){
        recipeBook.add(new SingleRecipe("Title", new ArrayList<String>(), Good, NorthAmerica));
        recipeBook.add(new SingleRecipe("Title2", new ArrayList<String>(), Poor, Asian));
        recipeBook.add(new SingleRecipe("Title3", new ArrayList<String>(), Good, SouthAmerica));
        assertTrue(recipeBook.remove("Title") );//remove one recipe from the recipe;
        assertEquals(2,recipeBook.getRecipes().size());
        assertEquals(2,recipeBook.getRecipeNum());
    }

    @Test
    void removeFalseTest(){
        recipeBook.add(new SingleRecipe("Title", new ArrayList<String>(), Good, NorthAmerica));
        recipeBook.add(new SingleRecipe("Title2", new ArrayList<String>(), Poor, Asian));
        recipeBook.add(new SingleRecipe("Title3", new ArrayList<String>(), Good, SouthAmerica));
        assertFalse(recipeBook.remove("Title15") );//remove one recipe from the recipe;
        assertEquals(3,recipeBook.getRecipes().size());
        assertEquals(3,recipeBook.getRecipeNum());
    }

    @Test
    void searchRecipeTest() {
        try{
            recipeBook.searchRecipe("Title");
        }
        catch (NoMatch noMatch){
            //do nothing
        }
        recipeBook.add(new SingleRecipe("Title", new ArrayList<String>(), Good, NorthAmerica));
        try{
            isNull(recipeBook.searchRecipe("Title"));
        }
        catch (NoMatch noMatch){
            fail("Should find the Recipe");
        }
        try{
            assertEquals("Good", recipeBook.searchRecipe("Title").getRating().toString());
        }
        catch (NoMatch noMatch){
            //do nothing
        }
    }

    @Test
    void searchMultiRecipeTest() {
        recipeBook.add(new SingleRecipe("Title", new ArrayList<String>(), Good, NorthAmerica));
        recipeBook.add(new SingleRecipe("Title3", new ArrayList<String>(), Good, NorthAmerica));
        recipeBook.add(new SingleRecipe("Title5", new ArrayList<String>(), Good, NorthAmerica));
        try {
            recipeBook.searchRecipe("Title5");
        } catch (NoMatch noMatch){
            fail("Should find the recipe");
        }
        try {
            assertEquals("Good", recipeBook.searchRecipe("Title5").getRating().toString());
        } catch(NoMatch noMatch){
            //do nothing
        }
        try {
            assertEquals(Good, recipeBook.searchRecipe("Title5").getRating());
        } catch (NoMatch noMatch){
            //do nothing
        }
        try {
            assertEquals(NorthAmerica, recipeBook.searchRecipe("Title5").getCuisine());
        } catch (NoMatch noMatch){
            //do nothing
        }
        try {
            assertEquals("NorthAmerica", recipeBook.searchRecipe("Title5").getCuisine().toString());
        } catch (NoMatch noMatch){
            //do nothing
        }
    }

    @Test
    void searchMultiRecipeTest2() {
        recipeBook.add(new SingleRecipe("Title", new ArrayList<String>(), Good, NorthAmerica));
        recipeBook.add(new SingleRecipe("Title3", new ArrayList<String>(), Good, NorthAmerica));
        recipeBook.add(new SingleRecipe("Title5", new ArrayList<String>(), Good, NorthAmerica));
        try {
            recipeBook.searchRecipe("Titledsa");
        } catch (NoMatch noMatch){
            //do nothing
        }
    }

    @Test
    void addIngredientsTest(){
        recipeBook.addIngredients("Beef");
        assertEquals(1,recipeBook.getMyIngredientsNum());
    }

    @Test
    void removeIngredientsTest(){
        recipeBook.addIngredients("Beef");
        recipeBook.removeIngredients("Beef");
        assertEquals(0,recipeBook.getMyIngredientsNum());
    }

    @Test
    void removeIngredientsDupTest(){
        recipeBook.addIngredients("Beef");
        recipeBook.addIngredients("Beef");
        recipeBook.addIngredients("Beef");
        assertEquals(1,recipeBook.getMyIngredientsNum());
    }


    @Test
    void whatICanMakeTest(){
        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add("Beef");
        recipeBook.add(new SingleRecipe("Title", ingredients, Good, NorthAmerica));
        recipeBook.addIngredients("Beef");
        assertEquals(1,recipeBook.whatICanMake().size());
    }

    @Test
    void whatICanMakeTestNotEnough(){
        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add("Beef");
        ingredients.add("Egg");
        recipeBook.add(new SingleRecipe("Title", ingredients, Good, NorthAmerica));
        recipeBook.addIngredients("Beef");
        assertEquals(0,recipeBook.whatICanMake().size());
    }

    @Test
    void whatICanMakeTestMultiIngredients(){
        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add("Beef");
        ingredients.add("Egg");
        recipeBook.add(new SingleRecipe("Title", ingredients, Good, NorthAmerica));
        recipeBook.addIngredients("Beef");
        recipeBook.addIngredients("Egg");
        assertEquals(1,recipeBook.whatICanMake().size());
    }

    @Test
    void whatICanMakeTestMoreIHave(){
        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add("Beef");
        recipeBook.add(new SingleRecipe("Title", ingredients, Good, NorthAmerica));
        recipeBook.addIngredients("Beef");
        recipeBook.addIngredients("Egg");
        assertEquals(2,recipeBook.getIngredientsIHave().size());
        assertEquals(1,recipeBook.whatICanMake().size());
    }

    @Test
    void whatICanMakeTestDiffIngredients(){
        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add("Bacon");
        ingredients.add("Chicken");
        recipeBook.add(new SingleRecipe("Title", ingredients, Good, NorthAmerica));
        recipeBook.addIngredients("Beef");
        recipeBook.addIngredients("Egg");
        assertEquals(0,recipeBook.whatICanMake().size());
    }

    @Test
    void whatICanMakeTestDiffIngredients2(){
        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add("Bacon");
        ingredients.add("Egg");
        recipeBook.add(new SingleRecipe("Title", ingredients, Good, NorthAmerica));
        recipeBook.addIngredients("Beef");
        recipeBook.addIngredients("Egg");
        assertEquals(0,recipeBook.whatICanMake().size());
    }
}

