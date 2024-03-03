package model;

import model.products.Product;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

/*
A list of items interpreted as an inventory, this holds everything the player currently owns.
 */
public class Inventory implements Writable {

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

    //MODIFIES: this
    //EFFECTS: Removes item at index from product list.
    public void removeProduct(int index) {
        if (index < getSize()) {
            prodList.remove(index);
        }
    }

    //MODIFIES: this
    //EFFECTS: Removes items older than 2 weeks.
    public int dropCheck() {
        int dropped = 0;
        for (int i = 0; i < getSize(); i++) {
            if (prodList.get(i).getTimeInStore() >= 14) {
                removeProduct(i);
                dropped++;
            }
        }
        return dropped;
    }

    @Override
    public JSONObject toJson() {
        JSONObject invData = new JSONObject();
        JSONArray prodListData = new JSONArray();

        for (Product p : prodList) {
            prodListData.put(p.toJson());
        }

        invData.put("prodList", prodListData);
        return invData;
    }

    //getters
    public int getSize() {
        return prodList.size();
    }

    public ArrayList<Product> getProducts() {
        return prodList;
    }

    //setters
    public void setProducts(ArrayList<Product> p) {
        prodList = p;
    }


}
