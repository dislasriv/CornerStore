package model.products;


import org.json.JSONObject;
import persistence.Writable;


/*
A product that can be bought in a store, these are added to the players inventory when bought.
 */
public class Product implements Writable {

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

    @Override
    //EFFECTS: Halves value of item if age >= expiry.
    public String toString() {
        return "A " + name + " it costs $" + cost + " to buy and sells for $"
                + salePrice + ", it goes on clearance " + expiryDate + " days after you buy it."
                 + " It gives you " + expValue + " exp when you sell it.";
    }

    //EFFECTS: Reconstructs the product, resetting the clone's dynamic stats.
    @Override
    public Product clone() {
        return new Product(name, cost, expiryDate, salePrice, lvlReq, expValue, unlockCost);
    }

    //EFFECTS: Converts this product instance into a JSON object.
    @Override
    public JSONObject toJson() {
        JSONObject prodToWrite = new JSONObject();

        //put all fields
        prodToWrite.put("name", name);
        prodToWrite.put("timeInStore", timeInStore);
        prodToWrite.put("cost", cost);
        prodToWrite.put("expiryDate", expiryDate);
        prodToWrite.put("salePrice", salePrice);
        prodToWrite.put("lvlReq", lvlReq);
        prodToWrite.put("expValue", expValue);
        prodToWrite.put("unlockCost", unlockCost);
        prodToWrite.put("clearance", clearance);


        return prodToWrite;
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

    public int getExpiryDate() {
        return expiryDate;
    }

    public boolean onClearance() {
        return clearance;
    }

    //setters
    public void setClearance(boolean c) {
        clearance = c;
    }

    public void setTimeInStore(int t) {
        timeInStore = t;
    }
}
