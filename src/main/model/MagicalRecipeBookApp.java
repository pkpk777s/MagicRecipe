package model;

import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.MagicalRecipeBookAppUI;

import javax.swing.*;

import static java.util.Objects.isNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Array;
import java.util.*;

public class MagicalRecipeBookApp {
    private RecipeBook recipeBook;
    private String filePath = "./data/recipeBook.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private MagicalRecipeBookAppUI magicalRecipeBookAppUI;

    // EFFECTS: run the Magic Recipe Book Application


    public MagicalRecipeBookApp(MagicalRecipeBookAppUI magicalRecipeBookAppUI) {
        this.magicalRecipeBookAppUI = magicalRecipeBookAppUI;
        jsonWriter = new JsonWriter(filePath);
        jsonReader = new JsonReader(filePath);
        init();
        //runApp();
    }

    /*
     * MODIFIES: this
     * EFFECTS: processes user input print out the start menu and lead to switch menu
     */
//
//    private void runApp() {
//        init();
//        String input = null;
//        while (true) {
//            startMenu();
//            input = scanner.next();
//            if (input.length() != 1) {
//                continue;
//            }
//            switchPage(input);
//        }
//    }

    /*
     * MODIFIES: this
     * EFFECTS: initialize the RecipeBook
     */

    private void init() {
        this.recipeBook = new RecipeBook();
    }

    /*
     * EFFECTS: display the start menu
     */

//    private void startMenu() {
//        System.out.println("Good Day!!\n");
//        System.out.println(String.format("There are %d recipes included!!", this.recipeBook.getRecipeNum()));
//        System.out.println("Please enter the number to select. (number only)\n");
//        System.out.println("1.  Show all recipes");
//        System.out.println("2.  View recipe by cuisine");
//        System.out.println("3.  search the recipe");
//        System.out.println("4.  Add a recipe");
//        System.out.println("5.  Remove a recipe");
//        System.out.println("6.  Add a ingredient");
//        System.out.println("7.  Remove a ingredient");
//        System.out.println("8.  Show what I can make");
//        System.out.println("9.  Show what I have");
//        System.out.println("s.  Save the data to file");
//        System.out.println("l.  Load the data from file");
//    }

    /*
     * MODIFIES: this
     * EFFECTS: switch the page to different functions
     */ //make sure is not over 25 lines

//    private void switchPage(String input) {
//        if (input.equals("s")) {
//            saveToFile();
//        } else if (input.equals("l")) {
//            loadJson();
//        } else if (!processSelectNum(input)) {
//            switchPage69(input);
//        } else if (Integer.parseInt(input) < 6) {
//            switchPage15(input);
//        }
//    }

    /*
     * MODIFIES: this
     * EFFECTS: switch the page to different functions
     */ //make sure is not over 25 lines

//    private void switchPage15(String input) {
//        switch (input) {
//            case "1":
//                showAllRecipe();
//                break;
//            case "2":
//                searchByCuisine();
//                break;
//            case "3":
//                searchByTitle();
//                break;
//            case "4":
//                addRecipe();
//                break;
//            case "5":
//                removeRecipe();
//                break;
//            default:
//                System.out.println("Invalid input!!!");
//                break;
//        }
//    }

    /*
     * MODIFIES: this
     * EFFECTS: switch the page to different functions
     */ //make sure is not over 25 lines

//    private void switchPage69(String input) {
//        switch (input) {
//            case "6":
//                addIngredient();
//                break;
//            case "7":
//                removeIngredient();
//                break;
//            case "8":
//                showWhatIcanMake();
//                break;
//            case "9":
//                showWhatIHave();
//                break;
//            default:
//                System.out.println("Invalid input!!!");
//                break;
//        }
//    }

    /*
     * REQUIRES: num > 0
     * EFFECTS: display the recipe details
     */

    private void selectRecipe(String num, SingleRecipe recipe) {
        magicalRecipeBookAppUI.getLog().append("Title: " + recipe.getTitle() + "\n");
        magicalRecipeBookAppUI.getLog().append("Ingredient: \n");
        for (String next : recipe.getIngredients()) {
            magicalRecipeBookAppUI.getLog().append(next + " ");
        }
        magicalRecipeBookAppUI.getLog().append("\n");
        magicalRecipeBookAppUI.getLog().append("Rating: " + recipe.getRating() + "\n");
        magicalRecipeBookAppUI.getLog().append("Rating: " + recipe.getCuisine() + "\n");
    }

    /*
     * EFFECTS: switch the page to different functions
     */

    public void showAllRecipe() {
        String tempInput = "";
        for (int i = 0; i < this.recipeBook.getRecipes().size(); i++) {
            magicalRecipeBookAppUI.getLog().append(i + ". " + this.recipeBook.getRecipes().get(i) + "\n");
        }
        if (!(this.recipeBook.getRecipeNum() == 0)) {
            tempInput = processInput((String)JOptionPane.showInputDialog(new JFrame(),"Input Q "
                    + "to back to previous menus. \nOr input number check the detail"));
        } else {
            magicalRecipeBookAppUI.getLog().append("No recipes include!!\n");
        }
        if (tempInput == "") {
            return;
        }
        if (tempInput.equals("Q")) {
            return;
        } else if (Integer.parseInt(tempInput) >= this.recipeBook.getRecipes().size()) {
            return;
        }
        if (processSelectNum(tempInput)) {
            selectRecipe(tempInput, this.recipeBook.getRecipes().get(Integer.parseInt(tempInput)));
        }
    }

    /*
     * EFFECTS: return True if it is a number otherwise return false
     */

    private Boolean processSelectNum(String select) {
        for (char k : select.toCharArray()) {
            if (!(k >= 48 && k <= 57)) {
                return false;
            }
        }
        return true;
    }

    /*
     * REQUIRES: input contains only number and alphabets
     * EFFECTS: change the name to the first case Uppercase others lowercase
     */

    private String processInput(String input) {
        if (checkString(input)) {
            this.magicalRecipeBookAppUI.getLog().append("Invalid input !! (number, alphabet only and no space)");
            return null;
        }
        input = input.toLowerCase();
        String tempInput = "";
        if (input.charAt(0) >= 97 && input.charAt(0) <= 122) {
            tempInput += input.charAt(0);
            tempInput = tempInput.toUpperCase();
            for (int i = 1; i < input.length(); i++) {
                tempInput += input.charAt(i);
            }
        } else if (input.charAt(0) >= 48 && input.charAt(0) <= 57) {
            return input;
        }
        return tempInput;
    }

    /*
     * EFFECTS: let users input the cuisine and search from the recipe book then display
     */

    public void searchByCuisine() {
        String tempInput = "";
        tempInput = (String) JOptionPane.showInputDialog(new JFrame(),"Input the cuisine you want: ");
        String cuisine = processInput(tempInput);
        int i = 0;
        if (isNull(cuisine)) {
            return;
        }
        for (SingleRecipe next : this.recipeBook.getRecipes()) {
            if (next.getCuisine().toString().equals(cuisine)) {
                this.magicalRecipeBookAppUI.getLog().append(i + ". " + next + "\n");
                i++;
            }
        }
    }

    /*
     * EFFECTS: let users input the title and search from the recipe book then display
     */

    public void searchByTitle() {
        String title = (String) JOptionPane.showInputDialog(new JFrame(),"Input the title: ");
        SingleRecipe recipe = null;
        try {
            recipe = this.recipeBook.searchRecipe(title);
        } catch (NoMatch noMatch) {
            this.magicalRecipeBookAppUI.getLog().append("No match found\n");
            return;
        }
        this.magicalRecipeBookAppUI.getLog().append("Title: " + recipe.getTitle() + "\n");
        this.magicalRecipeBookAppUI.getLog().append("Ingredient: " + "\n");
        for (String next : recipe.getIngredients()) {
            this.magicalRecipeBookAppUI.getLog().append(next + " ");
        }
        this.magicalRecipeBookAppUI.getLog().append("\n");
        this.magicalRecipeBookAppUI.getLog().append("Rating: " + recipe.getRating() + "\n");
        this.magicalRecipeBookAppUI.getLog().append("Rating: " + recipe.getCuisine() + "\n");
        this.magicalRecipeBookAppUI.getLog().append("Input anything to back to previous menus.\n");
    }

    /*
     * MODIFIES: this
     * EFFECTS: let users input the recipe detail and create a new recipe added to recipe book
     */

    public void addRecipe() {
        String title;
        List<String> ingredients;
        Rating rating = null;
        Cuisine cuisine = Cuisine.Asian;
        title = processInput((String) JOptionPane.showInputDialog(new JFrame(),"Input the title: "));
        if (isNull(title) || title == "") {
            this.magicalRecipeBookAppUI.getLog().append("Invalid input! \n");
            return;
        }
        ingredients = inputIngredients();
        rating = inputRating();
        if (isNull(rating)) {
            return;
        }
        cuisine = inputCuisine();
        if (isNull(cuisine)) {
            return;
        }
        this.recipeBook.add(new SingleRecipe(title,ingredients,rating,cuisine));
        printSuc();
        EventLog.getInstance().logEvent(new Event("Add recipe"));
    }

    /*
     * EFFECTS: let users input the recipe ingredients and return them as an arraylist
     */

    private List<String> inputIngredients() {
        List<String> ingredients = new ArrayList<>();
        String temp = "";
        while (true) {
            temp = (String) JOptionPane.showInputDialog(new JFrame(),"Input the ingredients: (enter 'q' to end)");
            if (isNull(temp)) {
                this.magicalRecipeBookAppUI.getLog().append("Invalid input! \n");
                continue;
            }
            if (temp.equals("Q") || temp.equals("q")) {
                break;
            }
            if (temp.length() == 0) {
                this.magicalRecipeBookAppUI.getLog().append("Empty input! \n");
                continue;
            }
            ingredients.add(temp);
        }
        return ingredients;
    }

    /*
     * EFFECTS: let users input the recipe rating find the right enum and return
     */

    private Rating inputRating() {
        String temp = "";
        temp = processInput((String) JOptionPane.showInputDialog(new JFrame(),
                "Input the Rating: "));
        for (Rating next : Rating.values()) {
            if (next.toString().equals(temp)) {
                return next;
            }
        }
        return null;
    }

    /*
     * EFFECTS: let users input the recipe cuisine find the right enum and return
     */

    private Cuisine inputCuisine() {
        String temp = "";
        temp = processInput((String) JOptionPane.showInputDialog(new JFrame(),
                "Input the Cuisine: "));
        for (Cuisine next : Cuisine.values()) {
            if (next.toString().equals(temp)) {
                return next;
            }
        }
        return null;
    }

    /*
     * EFFECTS: print successful adding message and input anything to continue
     */

    private void printSuc() {
        this.magicalRecipeBookAppUI.getLog().append("1 recipe added!!\n");
    }

    /*
     * MODIFIES: this
     * EFFECTS: let users input the title of the recipe and remove it from the recipe book
     */

    public void removeRecipe() {
        String temp = processInput((String) JOptionPane.showInputDialog(new JFrame(),
                "Input the recipe title you want to remove: "));
        if (isNull(temp)) {
            return;
        }
        EventLog.getInstance().logEvent(new Event("Recipe removed"));
        this.recipeBook.remove(temp);
    }

    /*
     * MODIFIES: this
     * EFFECTS: let users input the ingredients they have and add it into the list
     */

    public void addIngredient() {
        String temp = processInput((String) JOptionPane.showInputDialog(new JFrame(),
                "Input the ingredient you have: "));
        if (isNull(temp)) {
            return;
        }
        this.recipeBook.addIngredients(temp);
        EventLog.getInstance().logEvent(new Event("Ingredient added"));
    }

    /*
     * MODIFIES: this
     * EFFECTS: let users input the ingredients they do not have anymore and remove it into the list
     */

    public void removeIngredient() {
        String temp = processInput((String) JOptionPane.showInputDialog(new JFrame(),
                "Input the ingredient you have: "));
        if (isNull(temp)) {
            return;
        }
        this.recipeBook.removeIngredients(temp);
        EventLog.getInstance().logEvent(new Event("Ingredient removed"));
    }

    /*
     * EFFECTS: compare the ingredients I have and the recipes then display all recipes I can make with
     * current ingredients
     */

    public void showWhatIcanMake() {
        int i = 0;
        for (String next : this.recipeBook.whatICanMake()) {
            this.magicalRecipeBookAppUI.getLog().append(i + ". " + next + "\n");
            i++;
        }
    }

    /*
     * EFFECTS: display what ingredients I have
     */

    public void showWhatIHave() {
        int i = 0;
        for (String next : this.recipeBook.getIngredientsIHave()) {
            this.magicalRecipeBookAppUI.getLog().append(i + ". " + next + "\n");
            i++;
        }
    }

    /*
     * EFFECTS: save the data to file
     */
    public void saveToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(this.recipeBook);
            jsonWriter.close();
            this.magicalRecipeBookAppUI.getLog().append("Data Saved...\n");
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist!");
        }
    }


    public void loadJson() {
        try {
            this.recipeBook = jsonReader.read();
            this.magicalRecipeBookAppUI.getLog().append("Data loaded...\n");
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist!");
        } catch (IOException e) {
            System.err.println("Error!!!");
        } catch (JSONException e) {
            System.err.println("Error!!!");
        }
    }


    /*
     * EFFECTS: return true if the string is alphabets (UPPERCASE, lowercase) or numbers
     */
    private Boolean checkString(String string) {
        for (char k : string.toCharArray()) {
            if (k >= 48 && k <= 57) {
                // do nothing
            } else if (k >= 65 && k <= 90) {
                // do nothing
            } else if (!(k >= 97 && k <= 122)) {
                return true;
            }
        }
        return false;
    }

}