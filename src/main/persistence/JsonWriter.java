package persistence;

import model.RecipeBook;
import model.SingleRecipe;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {
    private PrintWriter printWriter;
    private String filePath;
    private static final int TAB = 4;

    public JsonWriter(String filePath) {
        this.filePath = filePath;
    }

    public void open() throws FileNotFoundException {
        printWriter = new PrintWriter(new File(filePath));
    }

    public void write(RecipeBook recipeBook) {
        JSONObject json = recipeBook.toJson();
        saveToFile(json.toString(TAB));
    }

    public void close() {
        printWriter.close();
    }

    private void saveToFile(String json) {
        printWriter.print(json);
    }

}
