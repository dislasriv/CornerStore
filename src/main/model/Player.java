package model;

/*
A class holding all the statistics for a player and their inventory.

fields:
- level --> the level of the player
- money --> how much money the player has left.
- Inventory --> a reference to the players list of items.
- exp --> experience points of the player.
 */

import model.products.Product;

public class Player {
    public static final int INITIAL_MONEY = 30;

    private int level;
    private int money;
    private Inventory inv;
    private int exp;

    //EFFECTS: creates a new player with an empty inventory
    public Player() {
        level = 0;
        money = INITIAL_MONEY;
        inv = new Inventory();
        exp = 1;
    }

    //MODIFIES: this
    //EFFECTS: Sets the level of the player to floor(log_3(exp)), returns true if level number changes since
    //the last time the method was called.
    public boolean checkLevelUp() {
        int clev = level;

        //cast log base 3 to int
        level = (int) (Math.log(exp) / Math.log(3));

        return (clev != level);
    }

    //MODIFIES: this
    //EFFECTS: Adds to the amount of money the player has by amount, amount can be positive or negative.
    public void modifyMoney(int amount) {
        money += amount;
    }

    //REQUIRES: index < inv.getSize()
    //MODIFIES: this, Inventory.
    //EFFECTS: Removes the item at the given index from the inventory
    // after calling modifyMoney() with the sale value of the item.
    public void sellProduct(int index) {
        //works since a reference to the object is given by the getter
        Product prod = inv.getProducts().get(index);
        modifyMoney(prod.getSalePrice());
        //remove product
        inv.getProducts().remove(index);
    }

    //REQUIRES: amount >= 0
    //MODIFIES: this
    //EFFECTS: adds exp amount to player.
    public void gainExp(int amount) {
        exp += amount;
    }

    //MODIFIES: this, Inventory
    //EFFECTS: adds item to inventory field
    public void addItemToInventory(Product item) {
        inv.addProduct(item);
    }

    //EFFECTS: Prints the player's stats
    public String toString() {
        return "You are level " + level + " and have $" + money + ".";
    }


    //getters
    public int getLevel() {
        return level;
    }

    public int getMoney() {
        return money;
    }

    public Inventory getInventory() {
        return inv;
    }

    public int getExp() {
        return exp;
    }
}
