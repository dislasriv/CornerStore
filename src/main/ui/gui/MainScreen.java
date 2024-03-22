package ui.gui;

import ui.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* class that handles all behaviour of the starting screen*/
public class MainScreen extends GenericScreen implements ActionListener {

    //static fields
    public static final String SAVE_COMMAND = "save";
    public static final String LOAD_COMMAND = "load";
    public static final String INV_COMMAND = "inv";
    public static final String OPTIONS_COMMAND = "options";
    public static final String PLAY_COMMAND = "play";


    //other screens
    private OptionsScreen optionsScreen;

    //components
    private JLabel dayStats;

    //EFFECTS: constructs the main screen of the GUI, essentially starts the program
    public MainScreen() {
        super(null, new App());
        initMainScreen();
    }

    //MODIFIES: this
    //EFFECTS: Sets up the main screen JFrame in its entirety
    public void initMainScreen() {
        initButtons();
        thisScreen.setVisible(true);
    }


    //MODIFIES: this
    //EFFECTS: Initializes buttons and adds them to mainPanel
    public void initButtons() {

        initDataButtons();

        //if more labels are add collapse this into a method
        dayStats = new JLabel(app.beginDay());
        dayStats.setBounds(110, 400, 500, 100);
        thisPanel.add(dayStats);

        //if more buttons are added collapse this into a method
        Button store = new Button("Begin Day!");
        store.setBounds(0, 415, 100, 50);
        store.setActionCommand(PLAY_COMMAND);
        store.addActionListener(this);
        thisPanel.add(store);
    }

    //MODIFIES: this
    //EFFECTS: Creates all of the buttons for load / save, adds them to main panel
    public void initDataButtons() {
        Button saveBtn = new Button("Save game");
        saveBtn.setBounds(0, 0, 100, 50);
        saveBtn.setActionCommand(SAVE_COMMAND);
        saveBtn.addActionListener(this);
        thisPanel.add(saveBtn);

        Button loadBtn = new Button("Load Game");
        loadBtn.setBounds(0, 50, 100, 50);
        loadBtn.setActionCommand(LOAD_COMMAND);
        loadBtn.addActionListener(this);
        thisPanel.add(loadBtn);

        Button inv = new Button("Inventory");
        inv.setBounds(300, 0, 100, 50);
        inv.setActionCommand(INV_COMMAND);
        inv.addActionListener(this);
        thisPanel.add(inv);

        Button store = new Button("Buy Items");
        store.setBounds(500, 0, 100, 50);
        store.setActionCommand(OPTIONS_COMMAND);
        store.addActionListener(this);
        thisPanel.add(store);
    }


    //MODIFIES: this
    //EFFECTS: when an action is performed, this function
    //        will identify it and execute the correct method
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case PLAY_COMMAND:
                beginGuiDay();
                break;
            case SAVE_COMMAND:
                app.saveData();
                break;

            case LOAD_COMMAND:
                app.loadData();
                updateLabels();
                break;

            case INV_COMMAND:
                displayInventory();
                break;

            case OPTIONS_COMMAND:
                displayBuyOptions();
        }
    }

    //MODIFIES: this, App
    //EFFECTS: runs a day for the GUI version of the project
    public void beginGuiDay() {
        String thisDayStats = app.beginDay();
        app.incrementDay();
        updateLabels();
        dayStats.setText(thisDayStats);
    }

    //MODIFIES: this, InventoryScreen
    //EFFECTS: Display user's inventory on a new screen
    public void displayInventory() {
        thisScreen.setVisible(false);

        //this is poo design, find a way to change the jtextarea when the inventory is updated
        new InventoryScreen(this, app).initInvScreen();
    }

    //MODIFIES: this, OptionsScreen
    //EFFECTS: takes user to the product buying screen
    public void displayBuyOptions() {
        thisScreen.setVisible(false);
        new OptionsScreen(this, app).initOptionsScreen();
    }
}
