package ui;


/*
Main indefinitely runs the runDay function. This class specifies most of the UI. This game iterates infinitely over
steps called days, at the start of a day you can view your inventory and buy things.
When you 'start' the day, all of the products in your inventory have a chance to sell.
The cycle will then repeat.
 */

import model.Player;
import model.Store;
import model.products.Orange;
import model.products.Product;
import persistence.SaveLoader;
import persistence.SaveWriter;
import persistence.Writable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketOption;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final int RENT = 50;
    private static final String SAVE_PATH = "data/SaveData.json";

    private Player plr;
    private Store store;
    private int day;
    private Scanner input;


    //EFFECTS: Creates a new App with all relevant objects to play the game
    public App() {
        plr = new Player();
        store = new Store(plr);
        day = 0;
        input = new Scanner(System.in);
    }

    //EFFECTS: runs a "day" in the program
    public void runDay() {
        incrementDay();
        System.out.println("It is day " + day + " of your store!");
        System.out.println();
        //see how many items went on clearance
        clearanceCheck();
        checkDrop();
        if (day % 5 == 0) {
            chargeRent();
        }

        //loop breaks when the day starts
        while (true) {
            if (mainControl()) {
                System.out.println();
                break;
            }
        }
        System.out.println();
        beginDay();
        System.out.println("You have completed day " + day + " of your store!");
        System.out.println();
    }

    //EFFECTS: Runs the main screen of the program, the root of the control flow
    public boolean mainControl() {
        System.out.println(plr);
        System.out.println("To view your inventory type 'inv'.");
        System.out.println("To go to the store type 'store'.");
        System.out.println("To go to start the day write 'start'.");
        System.out.println("To load the previous save write 'load'.");
        System.out.println("To save the app state write 'save'.");
        System.out.println("To go to quit write 'quit'.");

        String i = input.nextLine();

        return mainControlInputProcess(i);
    }

    //EFFECTS: Processes input in main control
    public boolean mainControlInputProcess(String i) {
        switch (i.toLowerCase()) {
            case "inv":
                System.out.println("\nYour inventory:");
                printProducts(plr.getInventory().getProducts());
                break;
            case "store":
                RunStore storeProtocol = new RunStore(this, input, store, plr);
                storeProtocol.runStore();
                break;
            case "start":
                return true;
            case "load":
                loadData();
                break;
            case "save":
                saveData();
                break;
            case "quit":
                System.exit(0);
                break;
        }
        return false;
    }

    //MODIFIES: Store, Player
    //EFFECTS: starts the day, for every product, this method rolls the dice to see if it sells.
    public String beginDay() {
        int profit = 0;
        int sales = 0;

        for (int i  = 0; i < plr.getInventory().getProducts().size(); i++) {
            //40% chance to sell
            if (Math.random() * 10 <= 4) {
                Product thisProduct = plr.getInventory().getProducts().get(i);
                //increase profit
                profit += thisProduct.getSalePrice();
                //increase sales
                sales++;

                plr.modifyMoney(plr.getInventory().getProducts().get(i).getSalePrice());
                plr.getInventory().removeProduct(i);
                expSequence(thisProduct);

                i--;
                if (thisProduct.onClearance()) {
                    System.out.println("You sold a(n) " + thisProduct.getName() + " for $"
                            + thisProduct.getSalePrice() + ", it was on clearance.");
                    continue;
                }
                System.out.println("You sold a(n) " + thisProduct.getName() + " for $"
                        + thisProduct.getSalePrice() + "!");
            }
        }
        System.out.println("\nTotal Profit: " + profit);
        System.out.println("Total Sales: " + sales);
        return "This day:   Total Profit: " + profit + "      Total Sales: " + sales;
    }


    //EFFECTS: Loads data from SAVE_PATH, if IOException fires, returns error msg
    public void loadData() {
        try {
            SaveLoader loader = new SaveLoader(SAVE_PATH);
            store = loader.parseStore();
            plr = store.getPlr();
            day = loader.parseDay();
            System.out.println("Save file loaded!");
        } catch (IOException e) {
            System.out.println("File loading failed, status: brokey");
        }
    }

    //EFFECTS: Saves data to SAVE_PATH
    public void saveData() {
        try {
            SaveWriter fileWriter = new SaveWriter(SAVE_PATH);
            fileWriter.writeData(store, day);
            fileWriter.getWriter().close();
            System.out.println("Game saved!");
        } catch (FileNotFoundException e) {
            System.out.println("Could not save data, status: File brokey");
        }
    }

    //MODIFIES: Player
    //EFFECTS: prints a message stating the amount of items that went on clearance.
    public void clearanceCheck() {
        int cleared = 0;

        //loop through all products to see which ones are now on clearance
        for (Product p : plr.getInventory().getProducts()) {
            p.increaseTimeInStore();
            if (p.putOnClearance()) {
                cleared++;
            }
        }
        System.out.println(cleared + " items were put on clearance today!");
    }

    //EFFECTS: prints a message stating the amount of items that were thrown out.
    public void checkDrop() {
        int dropped = plr.getInventory().dropCheck();
        System.out.println(dropped + " items were thrown away today!");
    }


    //EFFECTS: prints a list of products and returns that string
    public String printProducts(List<Product> list) {
        int i = 1;
        String out = "";
        for (Product p : list) {
            System.out.println(i + ": " + p);
            out += i + ": " + p + "\n\n";
            i++;
        }
        System.out.println();
        return out;
    }

    //EFFECTS: adds exp and checks if player leveled  after a sale.
    public void expSequence(Product thisProduct) {
        plr.gainExp(thisProduct.getExpValue());
        if (plr.checkLevelUp()) {
            System.out.println("Level up!");
        }
    }

    //MODIFIES: Player
    //EFFECTS: If player does not afford rent game ends with a message, else a message is sent stating that the player
    //         has been charged.
    public void chargeRent() {
        if (plr.getMoney() < RENT) {
            System.out.println("Its been five days, you have $" + plr.getMoney() + ", rent is $" + RENT
                    + ", you are being shut down goodbye.");
            System.exit(0);
        }
        //charge as normal
        System.out.println("Its been five days, rent time! You have been charged $" + RENT + ".");
        plr.modifyMoney(-1 * RENT);
    }

    //MODIFIES: this
    //EFFECTS: Adds one to day, progresses the day
    public void incrementDay() {
        day++;
    }

    //getters
    public Player getPlayer() {
        return plr;
    }

    public Store getStore() {
        return store;
    }

    public int getDay() {
        return day;
    }



}
