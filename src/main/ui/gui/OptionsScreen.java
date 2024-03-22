package ui.gui;

import model.Store;
import model.products.Product;
import ui.App;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//all images are from my phone camera roll//

/* Screen displaying options of products the user can buy*/
public class OptionsScreen extends GenericScreen implements ActionListener {

    //fields
    private JLabel buyResponseImage;
    private JLabel statusText;


    //EFFECTS: Initializes the screen
    public OptionsScreen(GenericScreen toGoBackTo, App app) {
        super(toGoBackTo, app);
    }

    //MODIFIES: this
    //EFFECTS: Renders and displays the screen
    public void initOptionsScreen() {
        renderOptionsPane();
        thisScreen.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: Creates a pane of buttons for buying products
    public void renderOptionsPane() {
        //create a new grid of options
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(9,1));
        gridPanel.setBounds(250, 100, 500, 250);
        gridPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //add stuff to panel
        makeButtonsForAllOptions(gridPanel);
        makeNavigationButtons();
        makeAllLabels();


        //add response image
        makeAllImages();

        thisPanel.add(gridPanel);
    }

    //MODIFIES: this
    //EFFECTS: creates a button for every product in availible options
    public void makeButtonsForAllOptions(JPanel putInto) {
        for (Product p : app.getStore().getAvailableOptions()) {
            Button newButton = new Button(p.getName());
            newButton.setSize(100, 50);
            //note action commands are the names of the products
            newButton.setActionCommand(p.getName());
            newButton.addActionListener(this);
            putInto.add(newButton);
        }
    }

    //MODIFIES: this
    //EFFECTS: renders all buttons that move the user to a different screen
    public void makeNavigationButtons() {
        Button back = new Button("Back");
        back.setBounds(0, 0, 100, 50);
        back.setActionCommand(RETURN_COMMAND);
        back.addActionListener(this);
        thisPanel.add(back);
    }

    //MODIFIES: this
    //EFFECTS: generates all image panes
    public void makeAllImages() {
        buyResponseImage = new JLabel();
        buyResponseImage.setBounds(200, 0, 100, 100);
        buyResponseImage.setBorder(new LineBorder(Color.BLACK,4));
        buyResponseImage.setIcon(new ImageIcon(new ImageIcon("data/Images/Smile.png")
                .getImage().getScaledInstance(100,100, java.awt.Image.SCALE_SMOOTH)));
        thisPanel.add(buyResponseImage);
    }

    //MODIFIES: this
    //EFFECTS: generate all labels
    public void makeAllLabels() {
        statusText = new JLabel("Welcome to the store!");
        statusText.setBounds(310,30, 1000, 50);
        thisPanel.add(statusText);
    }



    //EFFECTS: Handles all product buying events
    @Override
    public void actionPerformed(ActionEvent e) {
        checkIfBuying(e);
        switch (e.getActionCommand()) {
            case RETURN_COMMAND:
                goBack();
                break;
        }
    }


    //little note:
    //      Store and this class are coupled very tightly due to this method, maybe compare plr stats or something
    //      in order to reduce this coupling.
    //MODIFIES: this, App.Store
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
                    buyResponseImage.setIcon(new ImageIcon(new ImageIcon("data/Images/Valentino.jpeg")
                            .getImage().getScaledInstance(100,100, java.awt.Image.SCALE_SMOOTH)));
                    statusText.setText(Store.CANT_AFFORD);
                } else {
                    //print success picture
                    buyResponseImage.setIcon(new ImageIcon(new ImageIcon("data/Images/Mostaza.jpeg")
                            .getImage().getScaledInstance(100,100, java.awt.Image.SCALE_SMOOTH)));
                    statusText.setText(success);
                }
            }
        }
        updateLabels();
    }
}
