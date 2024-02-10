package model.products;


import static java.lang.Math.floor;

/*
A product that can be bought in a store, these are added to the inventory.

fields:
- name --> name of item
- timeInStore --> how many days you have owned the item.
- cost --> How much it costs the PLAYER to buy
- expiryDate --> how many days until the item goes on clearance
- salePrice --> How much the item can be SOLD for
- lvlReq --> the level required to unlock this item.
- expValue --> amount of exp gained from buying and selling the product.
 */
public class Product {

    private String name;
    private int timeInStore;
    private int cost;
    private int expiryDate;
    private int salePrice;
    private int lvlReq;
    private int expValue;
    private int unlockCost;
    private boolean clearance;

    //EFFECTS: Creates a new product with name, cost, salePrice
    public Product(String name, int cost, int expiryDate, int salePrice, int lvlReq, int expValue, int unlockCost) {
        this.name = name;
        timeInStore = 0;
        this.cost = cost;
        this.expiryDate = expiryDate;
        this.salePrice = salePrice;
        this.lvlReq = lvlReq;
        this.expValue = expValue;
        this.unlockCost = unlockCost;

        clearance = false;
    }

    //MODIFIES: this
    //EFFECTS: Reduces cost by 25%
    public boolean putOnClearance() {
        if (timeInStore >= expiryDate && !clearance) {
            salePrice = (int)(salePrice - salePrice * .25);
            clearance = true;
            return true;
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: Called at the start of each day cycle, increases timeInStore by 1.
    public void increaseTimeInStore() {
        timeInStore++;
    }

    //EFFECTS: Halves value of item if age >= expiry.
    public String toString() {
        return "A " + name + " it costs $" + cost + " to buy and sells for $"
                + salePrice + ", it goes on clearance " + expiryDate + " days after you buy it."
                 + " It gives you " + expValue + " exp when you sell it.";
    }

    //EFFECTS: Reconstructs the product, resetting the clone's dynamic stats.
    public Product clone() {
        return new Product(name, cost, expiryDate, salePrice, lvlReq, expValue, unlockCost);
    }


    //getters
    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public int getLvlReq() {
        return lvlReq;
    }

    public int getExpValue() {
        return expValue;
    }

    public int getTimeInStore() {
        return timeInStore;
    }

    public int getUnlockCost() {
        return unlockCost;
    }

    public boolean onClearance() {
        return clearance;
    }

}