package model;

import model.products.Orange;
import model.products.OrangeJuice;
import model.products.Product;

import java.util.ArrayList;

/*
A store where you can buy stock or unlock new stock. Instantiated with app.

fields:
- availableOptions --> string commands for items you can currently buy, used by UI
- availableUnlocks --> string commands for unlockable items, used by UI.
- plr --> player variable used to access and modify the player when they interact with the store.
 */
public class Store {
    public static final String CANT_AFFORD = "You cannot afford this.";
    public static final String DNE = "This is is not a valid purchase option.";
    public static final String SUCCESSFUL_BUY = "You bought a(n) ";
    public static final String SUCCESSFUL_UNLOCK = "You unlocked ";
    public static final String TOO_LOW_LEVEL = "Your level is too low! You must be level ";

    //fields
    private ArrayList<Product> availableOptions;
    private ArrayList<Product> availableUnlocks;
    private Player plr;

    //EFFECTS: Creates a new store
    public Store(Player plr) {
        //initialize fields
        availableOptions = new ArrayList<Product>();
        availableOptions.add(new Orange());

        availableUnlocks = new ArrayList<Product>();
        availableUnlocks.add(new OrangeJuice());

        this.plr = plr;
    }

    //EFFECTS: If the string has a corresponding product in availableOptions regardless of case...
    //              If plr.getMoney() >= Product.getCost();
    //                    method takes the cost of the product from the player, and adds a clone instance of the product
    //                    to the inventory. returns success
    //                    message.
    //              If not enough money return error string
    //         If it is not the method returns en error string.
    public String buyProduct(String choice) {
        //look for product
        Product toClone = null;
        for (Product p : availableOptions) {
            if (p.getName().toLowerCase().equals(choice.toLowerCase())) {
                toClone = p;
                break;
            }
        }
        if (toClone == null) {
            return DNE;
        }
        //check money
        if (plr.getMoney() >= toClone.getCost()) {
            plr.modifyMoney(-1 * toClone.getCost());
            plr.getInventory().addProduct(toClone.clone());
            return SUCCESSFUL_BUY + toClone.getName() + ".";
        } else {
            return CANT_AFFORD;
        }
    }


    //MODIFIES: this
    //EFFECTS: If string has a corresponding product is in availableUnlocks
    //                  If level is too low return error message stating this.
    //                 else If player can afford upgrade...
    //                        take the cost of the unlock from the player, move product to available options.
    //                  else return relevant error string.
    //         If it is not in availableUnlocks return error message stating this.
    public String unlockProduct(String choice) {
        //look for unlock
        Product toUnlock = null;
        int i = 0;
        for (i = 0; i < availableUnlocks.size(); i++) {
            if (availableUnlocks.get(i).getName().toLowerCase().equals(choice.toLowerCase())) {
                toUnlock = availableUnlocks.get(i);
                break;
            }
        }
        if (toUnlock == null) {
            return DNE;
        }
        //check level
        if (plr.getLevel() >= toUnlock.getLvlReq()) {
            if (plr.getMoney() >= toUnlock.getUnlockCost()) {
                //everything nominal
                plr.modifyMoney(-1 * toUnlock.getUnlockCost());
                availableOptions.add(toUnlock);
                availableUnlocks.remove(i);
                return SUCCESSFUL_UNLOCK + toUnlock.getName() + ".";
            } else {
                return CANT_AFFORD;
            }
        } else {
            return TOO_LOW_LEVEL + toUnlock.getLvlReq() + ".";
        }
    }

    //getters
    public ArrayList<Product> getAvailibleOptions() {
        return availableOptions;
    }

    public ArrayList<Product> getAvailibleUnlocks() {
        return availableUnlocks;
    }

    public Player getPlr() {
        return plr;
    }
}
