package ui.gui;

import ui.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* class that handles all behaviour of the starting screen*/
public class MainScreen implements ActionListener {

    //static fields
    public static final String SAVE_COMMAND = "save";
    public static final String LOAD_COMMAND = "load";
    public static final String INV_COMMAND = "inv";
    public static final String OPTIONS_COMMAND = "options";

    //fields for main screen
    private JFrame mainScreen;
    private JPanel mainPanel;
    private App app;

    //other screens
    private InventoryScreen invScreen;
    private OptionsScreen optionsScreen;


    //EFFECTS: constructs the main screen of the GUI, essentially starts the program
    public MainScreen() {
        app = new App();
        initMainScreen();
        invScreen = new InventoryScreen(mainScreen, app);
        optionsScreen = new OptionsScreen(mainScreen, app);
    }

    //EFFECTS: Sets up the main screen JFrame in its entirety
    public void initMainScreen() {
        mainScreen = new JFrame("Corner Store");
        mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScreen.setPreferredSize(new Dimension(1000, 500));

        //center screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainScreen.setLocation((int)screenSize.getWidth() / 2 - 500, (int)screenSize.getHeight() / 2 - 250);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        initButtons();

        mainScreen.add(mainPanel);
        mainScreen.pack();
        mainScreen.setVisible(true);
        mainScreen.setResizable(false);
    }


    //EFFECTS: Initializes buttons and adds them to mainPanel
    public void initButtons() {
        initDataButtons();
    }

    //MODIFIES: this
    //EFFECTS: Creates all of the buttons for load / save, adds them to main panel
    public void initDataButtons() {
        Button saveBtn = new Button("Save game");
        saveBtn.setBounds(0, 0, 100, 50);
        saveBtn.setActionCommand(SAVE_COMMAND);
        saveBtn.addActionListener(this);
        mainPanel.add(saveBtn);

        Button loadBtn = new Button("Load Game");
        loadBtn.setBounds(0, 50, 100, 50);
        loadBtn.setActionCommand(LOAD_COMMAND);
        loadBtn.addActionListener(this);
        mainPanel.add(loadBtn);

        Button inv = new Button("Inventory");
        inv.setBounds(300, 0, 100, 50);
        inv.setActionCommand(INV_COMMAND);
        inv.addActionListener(this);
        mainPanel.add(inv);

        Button store = new Button("Buy Items");
        store.setBounds(500, 0, 100, 50);
        store.setActionCommand(OPTIONS_COMMAND);
        store.addActionListener(this);
        mainPanel.add(store);
    }


    //MODIFIES: this
    //EFFECTS: when an action is performed, this function
    //        will identify it and execute the correct method
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case SAVE_COMMAND:
                app.saveData();
                break;

            case LOAD_COMMAND:
                app.loadData();
                break;

            case INV_COMMAND:
                displayInventory();
                break;

            case OPTIONS_COMMAND:
                displayBuyOptions();
                break;
        }
    }

    //EFFECTS: Display user's inventory on a new screen
    public void displayInventory() {
        mainScreen.setVisible(false);
        invScreen.initInvScreen();
    }

    //EFFECTS: takes user to the product buying screen
    public void displayBuyOptions() {
        mainScreen.setVisible(false);
        optionsScreen.initOptionsScreen();
    }
}
