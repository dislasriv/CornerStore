package ui.gui.events;

import ui.App;

import java.awt.event.*;

/*
A class that extends and modifies the default java class WindowAdapter to print our logs when closing the screen
 */
public class MicroWindowListener extends WindowAdapter {
    //EFFECTS: calls the static method App.quitApp()
    @Override
    public void windowClosing(WindowEvent e) {
        App.quitApp();
    }
}
