package model;


import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.Element;
import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;
import static model.EventLog.getInstance;

public class RecipeBook {
    private List<SingleRecipe> recipeList;
    private List<String> ingredientsIHave;

    /*
     * EFFECTS: construct a new RecipeBook object.
     * Set two new empty recipeList and ingredientsIHave
     */
    public RecipeBook() {
        this.recipeList = new ArrayList<>();
        this.ingredientsIHave = new ArrayList<>();
    }
    /*
     * REQUIRES: singleRecipe is not empty, null and duplicated;
     * MODIFIES: this
     * EFFECTS: new SingleRecipe is added to the recipeList.
     */

    public void add(SingleRecipe singleRecipe) {
        this.recipeList.add(singleRecipe);
    }

    /*
     * REQUIRES: title is not empty String
     * MODIFIES: this
     * EFFECTS: the recipe named title is remove from the recipeList and true will be return if
     * successful otherwise return false;
     */
    public Boolean remove(String title) {
        for (int i = 0; i < this.recipeList.size(); i++) {
            if (this.recipeList.get(i).getTitle().equals(title)) {
                this.recipeList.remove(i);
                return true;
            }
        }
        return false;
    }

    /*
     * REQUIRES: title is not an empty String
     * EFFECTS: the recipe named title will be returned if there is a recipe named title
     * otherwise NoMatch Exception will be thrown
     */
    public SingleRecipe searchRecipe(String title) throws NoMatch {
        List<SingleRecipe> tempList = this.recipeList;
        for (SingleRecipe next : tempList) {
            if (next.getTitle().equals(title)) {
                return next;
            }
        }
        throw new NoMatch();
    }

    /*
     * REQUIRES: ingredient is not an empty String
     * MODIFIES: this
     * EFFECTS: the ingredient will be added to the ingredientsIHAVE and
     *  if the list has already had the ingredient it will skip
     */
    public void addIngredients(String ingredient) {
        if (this.ingredientsIHave.contains(ingredient)) {
            return;
        }
        this.ingredientsIHave.add(ingredient);
    }

    /*
     * REQUIRES: ingredient is not an empty String
     * MODIFIES: this
     * EFFECTS: the ingredient will be removed from the ingredientsIHAVE
     */
    public void removeIngredients(String ingredient) {
        this.ingredientsIHave.remove(ingredient);
    }

    /*
     * EFFECTS: return a new ArrayList which contains the recipes that can be made with the ingredients I have
     */
    public List<String> whatICanMake() {
        List<String> whatICanMake = new ArrayList<>();
        Boolean enough;
        for (SingleRecipe next : this.recipeList) {
            enough = true;
            if (next.getIngredientsNum() > this.ingredientsIHave.size()) {
                continue;
            }
            for (String temp : next.getIngredients()) {
                if (!this.ingredientsIHave.contains(temp)) {
                    enough = false;
                }
            }
            if (enough) {
                whatICanMake.add(next.getTitle());
            }
        }
        return whatICanMake;
    }

    public List<SingleRecipe> getRecipes() {
        return this.recipeList;
    }

    public List<String> getIngredientsIHave() {
        return this.ingredientsIHave;
    }

    public int getMyIngredientsNum() {
        return this.ingredientsIHave.size();
    }

    public int getRecipeNum() {
        return this.recipeList.size();
    }


    /*
     * EFFECTS: returns as json object
     */
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Recipes",bookToJson());
        return json;
    }

    /*
     * EFFECTS: make list to JSON array and return
     */
    private JSONArray bookToJson() {
        JSONArray jsonArray = new JSONArray();
        for (SingleRecipe next : this.recipeList) {
            jsonArray.put(next.toJson());
        }
        return jsonArray;
    }

    public static void allDone() {
        EventLog.getInstance().forEach(event -> System.out.println(event));
    }
}
