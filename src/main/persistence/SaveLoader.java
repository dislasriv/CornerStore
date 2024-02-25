package persistence;


// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import model.Inventory;
import model.Player;
import model.Store;
import model.products.Product;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

/*
Class with all loading operations
 */
public class SaveLoader {
    private String source;
    private JSONObject data;


    // EFFECTS: constructs reader to read from source file
    public SaveLoader(String source) throws IOException {
        this.source = source;
        data = new JSONObject(readFile(source));
    }

    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: Parses and returns the saved player
    private Player parsePlayer(JSONObject storeData) {
        JSONObject plrToLoad = storeData.getJSONObject("plr");
        Player newPlayer = new Player();

        //parse level, money and exp
        newPlayer.setLevel(plrToLoad.getInt("level"));
        newPlayer.setMoney(plrToLoad.getInt("money"));
        newPlayer.setExp(plrToLoad.getInt("exp"));

        //set inventory
        Inventory newInv = new Inventory();
        newInv.setProducts(parseListOfProduct(
                ((JSONObject)plrToLoad.getJSONObject("inv")).getJSONArray("prodList")));
        newPlayer.setInventory(newInv);

        return newPlayer;
    }

    //EFFECTS: Parses and returns the saved store, calls parsePlayer
    public Store parseStore() {
        //get the store object
        JSONObject storeData = data.getJSONObject("store");

        //parse the player, while generating a new store
        Store loadedStore = new Store(parsePlayer(storeData));
        //get availableOptions
        loadedStore.setAvailableOptions(parseListOfProduct(storeData.getJSONArray("availableOptions")));
        //load availableUnlocks
        loadedStore.setAvailableUnlocks(parseListOfProduct(storeData.getJSONArray("availableUnlocks")));
        return loadedStore;
    }

    //EFFECTS: parses a list of products
    public ArrayList<Product> parseListOfProduct(JSONArray jsonArray) {
        //declare new array to house loaded products
        ArrayList<Product> loadedArray = new ArrayList<Product>();

        for (Object jsonProduct : jsonArray) {
            JSONObject prodToLoad = (JSONObject) jsonProduct;
            String name = prodToLoad.getString("name");
            int timeInStore = prodToLoad.getInt("timeInStore");
            int cost = prodToLoad.getInt("cost");
            int expiry = prodToLoad.getInt("expiryDate");
            int salePrice = prodToLoad.getInt("salePrice");
            int lvlReq = prodToLoad.getInt("lvlReq");
            int expVal = prodToLoad.getInt("expValue");
            int unlockCost = prodToLoad.getInt("unlockCost");
            boolean clearance = prodToLoad.getBoolean("clearance");

            Product newProd = new Product(name, cost, expiry, salePrice, lvlReq, expVal, unlockCost);
            newProd.setClearance(clearance);
            newProd.setTimeInStore(timeInStore);
            loadedArray.add(newProd);
        }
        return loadedArray;
    }

    //EFFECTS: Parses and returns the saved date
    public int parseDay() {
        return data.getInt("day");
    }

    //getters
    public String getSource() {
        return source;
    }

    public JSONObject getData() {
        return data;
    }
}
