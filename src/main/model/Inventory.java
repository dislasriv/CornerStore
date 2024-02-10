package model;

import model.products.Product;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Product> prodList;

    //EFFECTS: Creates an empty inventory, created upon player generation.
    public Inventory() {
        prodList = new ArrayList<Product>();
    }

    //MODIFIES: this
    //EFFECTS: Adds the given item to the product list.
    public void addProduct(Product item) {
        prodList.add(item);
    }

    //REQUIRES: index < getSize()
    //MODIFIES: this
    //EFFECTS: Removes item at index from product list.
    public void removeProduct(int index) {
        prodList.remove(index);
    }

    //getters
    public int getSize() {
        return prodList.size();
    }

    public ArrayList<Product> getProducts() {
        return prodList;
    }

}