package ui;

import model.Player;
import model.Store;
import model.products.Product;
import ui.App;

import java.util.Scanner;

/*
A class made to separate store related UI from App. This class holds all of the specification
for the behaviour of the store screen.
 */
public class RunStore {

    //references to things generated in app
    private App app;
    private Scanner input;
    private Store store;
    private Player plr;


    //EFFECTS: Creates a new instance of this class
    public RunStore(App app, Scanner input, Store store, Player plr) {
        this.app = app;
        this.input = input;
        this.store = store;
        this.plr = plr;
    }


    //EFFECTS: Runs the store screen (sub-screen of main screen) indefinitely.
    public void runStore() {
        //loop breaks when quit
        while (true) {
            if (storeControl()) {
                break;
            }
        }
    }

    //EFFECTS: Goes through an iteration of the store screen.
    public boolean storeControl() {
        System.out.println();
        System.out.println(plr);
        System.out.println("This is the store!");
        System.out.println("Type 'options' to see what you can buy.");
        System.out.println("Type 'unlocks' to see what you can unlock.");
        System.out.println("Type 'back' to go back to the previous screen.");

        String i = input.nextLine();

        switch (i.toLowerCase()) {
            case "options":
                System.out.println("\nPossible Products:");
                app.printProducts(store.getAvailibleOptions());
                //product buying sequence
                tryBuy();
                break;
            case "unlocks":
                printUnlocks();
                //unlock buying sequence
                tryUnlock();
                break;
            case "back":
                return true;

        }
        return false;
    }

    //helper methods for options and unlocks cases above

    //EFFECTS: Prompts the player to buy an item.
    public void tryBuy() {
        System.out.println("What product do you want to buy (type name)?");
        System.out.println(store.buyProduct(input.nextLine()));
    }

    //EFFECTS: Prompts the player to unlock an item.
    public void tryUnlock() {
        System.out.println("What unlockable do you want to buy (type name)?");
        System.out.println(store.unlockProduct(input.nextLine()));
    }

    //EFFECTS: Prints the possible unlocks the player can buy
    public void printUnlocks() {
        System.out.println("\nPossible Unlocks:");
        int i = 1;
        for (Product p : store.getAvailibleUnlocks()) {
            System.out.println();
            System.out.println(i + ": " + p);
            System.out.println("It can be unlocked for $" + p.getUnlockCost() + ".");
        }
    }
}
