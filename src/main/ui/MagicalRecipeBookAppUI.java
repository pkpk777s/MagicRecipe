package ui;

import model.MagicalRecipeBookApp;

import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;

// A class which extends Jpanel and ActionListener. This class implements the main panel of the app and
// perform the action
public class MagicalRecipeBookAppUI extends JPanel implements ActionListener {

    JButton showAllRecipe;
    JButton searchByCuisine;
    JButton searchByTitle;
    JButton addRecipe;
    JButton removeRecipe;
    JButton addIngredient;
    JButton removeIngredient;
    JButton showWhatIcanMake;
    JButton showWhatIHave;
    JButton saveToFile;
    JButton loadFromFile;
    JButton clear;
    MagicalRecipeBookApp magicalRecipeBookApp;
    JTextArea log;


    // EFFECTS: implements the main UI and buttons
    public MagicalRecipeBookAppUI() {
        super(new BorderLayout());
        magicalRecipeBookApp = new MagicalRecipeBookApp(this);
        log = new JTextArea(20,40);
        log.setMargin(new Insets(7,7,7,7));
        log.setEditable(true);
        JScrollPane logScrollPane = new JScrollPane(log);

        createJButton1();
        createJButton2();
        clear = new JButton("Clear");
        clear.addActionListener(this);

        JPanel jpanel = new JPanel();
        JPanel jpanel2 = new JPanel();

        add2JPanel(jpanel, jpanel2);
        add(jpanel,BorderLayout.PAGE_START);
        add(jpanel2,FlowLayout.CENTER);
        add(logScrollPane, BorderLayout.PAGE_END);

    }

    //EFFECTS: override the actionPerformed and it will perform the action when the event happens
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clear) {
            log.setText("");
        } else {
            try {
                scanAction(e);
            } catch (Exception exc) {
                //do nothing
            }
        }
    }


    // EFFECTS: a helper function of actionPerformed
    private void scanAction(ActionEvent e) {
        if (e.getSource() == showAllRecipe) {
            this.magicalRecipeBookApp.showAllRecipe();
        } else if (e.getSource() == searchByCuisine) {
            this.magicalRecipeBookApp.searchByCuisine();
        } else if (e.getSource() == searchByTitle) {
            this.magicalRecipeBookApp.searchByTitle();
        } else if (e.getSource() == addRecipe) {
            this.magicalRecipeBookApp.addRecipe();
        } else if (e.getSource() == removeRecipe) {
            this.magicalRecipeBookApp.removeRecipe();
        } else if (e.getSource() == addIngredient) {
            this.magicalRecipeBookApp.addIngredient();
        } else if (e.getSource() == removeIngredient) {
            this.magicalRecipeBookApp.removeIngredient();
        } else if (e.getSource() == showWhatIcanMake) {
            this.magicalRecipeBookApp.showWhatIcanMake();
        } else if (e.getSource() == showWhatIHave) {
            this.magicalRecipeBookApp.showWhatIHave();
        } else if (e.getSource() == saveToFile) {
            this.magicalRecipeBookApp.saveToFile();
        } else if (e.getSource() == loadFromFile) {
            this.magicalRecipeBookApp.loadJson();
        }
    }

    // helper function of creating buttons and add them to action listener
    private void createJButton1() {
        showAllRecipe = new JButton("Show all receipes");
        showAllRecipe.addActionListener(this);
        searchByCuisine = new JButton("View recipe by cuisine");
        searchByCuisine.addActionListener(this);
        searchByTitle = new JButton("Search the recipe");
        searchByTitle.addActionListener(this);
        addRecipe = new JButton("Add a recipe");
        addRecipe.addActionListener(this);
        removeRecipe = new JButton("Remove a recipe");
        removeRecipe.addActionListener(this);
    }

    // EFFECTS: helper function of creating buttons and add them to action listener
    private void createJButton2() {
        addIngredient = new JButton("Add a ingredient");
        addIngredient.addActionListener(this);
        removeIngredient = new JButton("Remove a ingredient");
        removeIngredient.addActionListener(this);
        showWhatIcanMake = new JButton("Show what I can make");
        showWhatIcanMake.addActionListener(this);
        showWhatIHave = new JButton("Show what I have");
        showWhatIHave.addActionListener(this);
        saveToFile = new JButton("Save to file");
        saveToFile.addActionListener(this);
        loadFromFile = new JButton("Load from file");
        loadFromFile.addActionListener(this);
    }

    // REQUIRES: Jpanels are not null
    // EFFECTS: helper function of adding buttons to jpanel.
    private void add2JPanel(JPanel jpanel, JPanel jpanel2) {
        jpanel.add(showAllRecipe);
        jpanel.add(searchByCuisine);
        jpanel.add(searchByTitle);
        jpanel.add(addRecipe);
        jpanel.add(removeRecipe);
        jpanel.add(addIngredient);
        //
        jpanel2.add(removeIngredient);
        jpanel2.add(showWhatIcanMake);
        jpanel2.add(showWhatIHave);
        jpanel2.add(saveToFile);
        jpanel2.add(loadFromFile);
        jpanel2.add(clear);
    }

    // REQUIRES: window is not null
    // EFFECTS: helper function of adding background to window.
    public static void addBackground(JWindow window) {
        BufferedImage imageBuffer = null;
        try {
            imageBuffer = ImageIO.read(new File("./resource/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = imageBuffer.getScaledInstance(1000,900,10);
        ImageIcon imageIcon = new ImageIcon(image);

        JLabel jlabel = new JLabel();
        jlabel.setIcon(imageIcon);
        window.getContentPane().add(jlabel);
    }

    // EFFECTS: create the splash screen and user interface using multi thread
    public static void createAndShowGUI() throws IOException {
        JWindow window = new JWindow();
        addBackground(window);
        window.setSize(1000,900);
        window.setVisible(true);
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(2000);
                return null;
            }

            protected void done() {
                window.dispose();
                JFrame frame = new JFrame("MagicRecipeBookApp");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                MagicalRecipeBookAppUI magicalRecipeBookAppUI = new MagicalRecipeBookAppUI();
                frame.add(magicalRecipeBookAppUI);
                frame.pack();
                frame.setSize(1000,900);
                frame.setVisible(true);
            }
        };
        worker.execute();

    }

    public JTextArea getLog() {
        return this.log;
    }
}
