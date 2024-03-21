package ui.gui;

import model.Store;
import model.products.Product;
import ui.App;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/* Screen displaying options to buy*/
public class OptionsScreen extends GenericScreen implements ActionListener {

    //EFFECTS: Initializes the screen
    public OptionsScreen(JFrame toGoBackTo, App app) {
        super(toGoBackTo, app);
    }


    //EFFECTS: Renders and displays the screen
    public void initOptionsScreen() {
        List<Product> availibleOptions = app.getStore().getAvailableOptions();
        renderOptionsPane();
        thisScreen.setVisible(true);
    }

    //EFFECTS: Creates a pane of buttons for buying products
    public void renderOptionsPane() {
        //create a new grid of options
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(9,1));
        gridPanel.setBounds(250, 100, 500, 250);
        gridPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //add stuff to panel
        makeButtonsForAllOptions(gridPanel);

        thisPanel.add(gridPanel);
    }

    //EFFECTS: creates a button for every product in availible options
    public void makeButtonsForAllOptions(JPanel putInto) {
        for (Product p : app.getStore().getAvailableOptions()) {
            Button newButton = new Button(p.getName());
            newButton.setSize(100, 50);
            //note action commands are the names of the products
            newButton.setActionCommand(p.getName());
            putInto.add(newButton);
        }
    }


    //EFFECTS: Handles all product buying events
    @Override
    public void actionPerformed(ActionEvent e) {
        checkIfBuying(e);

    }


    //little note:
    //      Store and this class are coupled very tightly due to this method, maybe compare plr stats or something
    //      in order to reduce this coupling.
    //EFFECTS: checks if user clicked a buy button
    public void checkIfBuying(ActionEvent e) {

        //for all possible products, check if it is
        for (Product p : app.getStore().getAvailableOptions()) {
            //if action command is a clicked product then try to buy
            if (p.getName().equals(e.getActionCommand())) {
                Store storeInstance = app.getStore();
                String success = storeInstance.buyProduct(e.getActionCommand());


                if (success.equals(Store.CANT_AFFORD)) {
                    //print fail picture haha you suck
                } else {
                    //print success picture
                    System.out.println(success);
                }
            }

        }
    }
}
