package ui.gui;

import ui.App;

import javax.swing.*;
import java.awt.*;

/* captures all generic screen behaviour that all screens will share*/
public abstract class GenericScreen {

    //fields
    protected JFrame screenToGoBackTo;
    protected JFrame thisScreen;
    protected JPanel thisPanel;
    protected App app;

    //EFFECTS: Constructs a generic screen
    public GenericScreen(JFrame toGoBackTo, App app) {
        //set fields
        screenToGoBackTo = toGoBackTo;
        this.app = app;

        //make screen
        thisScreen = new JFrame("Corner Store");
        thisScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisScreen.setPreferredSize(new Dimension(1000, 500));

        //center
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        thisScreen.setLocation((int)screenSize.getWidth() / 2 - 500, (int)screenSize.getHeight() / 2 - 250);

        //set up content panel
        thisPanel = new JPanel();
        thisPanel.setLayout(null);
        thisScreen.add(thisPanel);

        //apply settings
        thisScreen.pack();
        thisScreen.setResizable(false);
    }
}
