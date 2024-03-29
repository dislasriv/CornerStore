package ui.gui;

import ui.App;
import ui.gui.events.MicroWindowListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/* captures all generic screen behaviour that all screens will share*/
public abstract class GenericScreen {
    //constants
    public static final String RETURN_COMMAND = "return";

    //fields
    protected GenericScreen screenToGoBackTo;
    protected JFrame thisScreen;
    protected JPanel thisPanel;
    protected JLabel plrDay;
    protected JLabel plrMoney;

    protected App app;


    //EFFECTS: Constructs a generic screen
    public GenericScreen(GenericScreen toGoBackTo, App app) {
        //make screen
        thisScreen = new JFrame("Corner Store");
        thisScreen.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

        thisScreen.addWindowListener(new MicroWindowListener());

        //set fields
        screenToGoBackTo = toGoBackTo;
        this.app = app;
        updateLabels();
    }

    //MODIFIES: this
    //EFFECTS: sets all of the universal labels for all screeens
    public void updateLabels() {

        if (plrDay == null) {
            plrDay = new JLabel("Day: " + app.getDay());
            plrDay.setBounds(800,0, 200, 50);
            thisPanel.add(plrDay);

            plrMoney = new JLabel("Money: " + app.getPlayer().getMoney() + "$");
            plrMoney.setBounds(800,15, 200, 50);
            thisPanel.add(plrMoney);
        } else {
            plrDay.setText("Day: " + app.getDay());
            plrMoney.setText("Money: " + app.getPlayer().getMoney() + "$");
        }

    }

    //MODIFIES: this, screenToGoBackTo
    //EFFECTS: returns the app to the active screen's screen to go back to. and updates its relevant labels
    public void goBack() {
        thisScreen.setVisible(false);
        screenToGoBackTo.updateLabels();
        screenToGoBackTo.getScreen().setVisible(true);
    }

    //getters
    public JFrame getScreen() {
        return thisScreen;
    }
}
