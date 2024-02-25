import model.Player;
import model.Store;
import model.products.Orange;
import model.products.OrangeJuice;
import model.products.Product;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
Class that tests the behaviour of store
 */
public class StoreTest {

    private Store newStore;
    private ArrayList<Product> defaultOps;
    private ArrayList<Product> defaultUns;
    private Player testPlr;

    @BeforeEach
    void setup() {
        testPlr = new Player();
        newStore = new Store(testPlr);
        defaultOps = new ArrayList<Product>();
        defaultUns = new ArrayList<Product>();


        defaultOps.add(new Orange());
        defaultUns.add(new OrangeJuice());
    }

    //testConstructor
    @Test
    void testConstructor() {
        assertEquals("Orange", newStore.getAvailibleOptions().get(0).getName());
        assertEquals("Orange Juice", newStore.getAvailibleUnlocks().get(0).getName());
        assertTrue(testPlr.equals(newStore.getPlr()));
    }

    //buyProduct
    @Test
    void testBuyProductNotAvailable() {
        assertEquals(Store.DNE, newStore.buyProduct("mooga"));
    }

    @Test
    void testBuyProductAvailableTooPoor() {
        testPlr.modifyMoney(-100000);
        assertEquals(Store.CANT_AFFORD, newStore.buyProduct("oRanGE"));
        assertEquals(Store.CANT_AFFORD, newStore.buyProduct("oraNge"));
    }

    @Test
    void testBuyProductAvailableCanAfford() {
        assertEquals(Store.SUCCESSFUL_BUY + "Orange.", newStore.buyProduct("ORange"));
        assertEquals("Orange", testPlr.getInventory().getProducts().get(0).getName());
        assertEquals(testPlr.INITIAL_MONEY -testPlr.getInventory().getProducts().get(0).getCost(),
                testPlr.getMoney());
    }

    //unlockProduct
    @Test
    void testUnlockProductNotFound() {
        assertEquals(Store.DNE, newStore.unlockProduct("mooga"));
    }


    @Test
    void testUnlockProductTooLowLevel() {
        assertEquals(Store.TOO_LOW_LEVEL + OrangeJuice.LVL_REQ + ".",
                newStore.unlockProduct("OrANge Juice"));
    }

    @Test
    void testUnlockProductTooPoor() {
        testPlr.modifyMoney(-100000);
        testPlr.gainExp(1000);
        testPlr.checkLevelUp();
        assertEquals(Store.CANT_AFFORD, newStore.unlockProduct("OrANge Juice"));
        assertEquals(Store.CANT_AFFORD, newStore.unlockProduct("OrANge Juice"));
    }

    @Test
    void testUnlockProductAllNormal() {
        //setup
        testPlr.modifyMoney(OrangeJuice.UNLOCK_COST);
        int cmoney = testPlr.getMoney();
        int clength = newStore.getAvailibleOptions().size();

        testPlr.gainExp(1000);
        testPlr.checkLevelUp();
        assertEquals(Store.SUCCESSFUL_UNLOCK + OrangeJuice.NAME + ".",
                newStore.unlockProduct("OrANge Juice"));

        assertEquals(cmoney - OrangeJuice.UNLOCK_COST, testPlr.getMoney());
        assertEquals(clength + 1, newStore.getAvailibleOptions().size());
    }

    //toJson
    @Test
    void testToJsonDefault() {
        JSONObject storeData = newStore.toJson();

        assertEquals(storeData.getJSONArray("availableOptions").get(0).toString(),
                newStore.getAvailibleOptions().get(0).toJson().toString());
        assertEquals(storeData.getJSONArray("availableUnlocks").get(0).toString(),
                newStore.getAvailibleUnlocks().get(0).toJson().toString());
        assertEquals(storeData.getJSONObject("plr").toString(),
                testPlr.toJson().toString());
    }

    @Test
    void testToJsonMulti() {
        Player newPlr = new Player();
        newPlr.setExp(202);
        newPlr.setMoney(123);
        newPlr.setLevel(13);

        Store newStore = new Store(newPlr);
        newStore.getAvailibleUnlocks().add(new Orange());

        JSONObject storeData = newStore.toJson();

        assertEquals(storeData.getJSONArray("availableOptions").get(0).toString(),
                newStore.getAvailibleOptions().get(0).toJson().toString());
        assertEquals(storeData.getJSONArray("availableUnlocks").get(0).toString(),
                newStore.getAvailibleUnlocks().get(0).toJson().toString());
        assertEquals(storeData.getJSONObject("plr").toString(),
                newPlr.toJson().toString());
    }
}
