package ui.gui;



import model.Inventory;
import ui.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* a class that displays a screen with the users inventory*/

public class InventoryScreen extends GenericScreen implements ActionListener {

    public static final String RETURN_COMMAND = "return";

    //EFFECTS: Initializes the screen
    public InventoryScreen(JFrame toGoBackTo, App app) {
        super(toGoBackTo, app);
    }

    //EFFECTS: Sets up the main screen JFrame in its entirety
    public void initInvScreen() {
        initUi();
        thisScreen.setVisible(true);
    }

    //EFFECTS: renders all the inventory UI
    public void initUi() {
        //get the inventory
        Inventory toDisplay = app.getPlayer().getInventory();

        //make a textarea
        JTextArea invText = new JTextArea(app.printProducts(toDisplay.getProducts()));
        invText.setEditable(false);

        //set up a scrollable pane
        JScrollPane inventoryPane = new JScrollPane(invText);
        inventoryPane.setBounds(250, 100, 500, 250);

        //exit btn
        Button back = new Button("Back");
        back.setBounds(0, 0, 100, 50);
        back.setActionCommand(RETURN_COMMAND);
        back.addActionListener(this);
        thisPanel.add(back);
        thisPanel.add(inventoryPane);
    }

    //EFFECTS: Handles inventory screen events
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case RETURN_COMMAND:
                goBackToMain();
                break;
        }
    }

    //EFFECTS: returns the app to the main screen.
    public void goBackToMain() {
        thisScreen.setVisible(false);
        screenToGoBackTo.setVisible(true);
    }
}
