package ui;


import javax.swing.*;

import static model.RecipeBook.allDone;
import static ui.MagicalRecipeBookAppUI.createAndShowGUI;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                try {
                    createAndShowGUI();
                } catch (Exception e) {
                    // do nothing
                }
            }
        });
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                allDone();
            }
        }, "Shutdown-thread"));
    }
}
