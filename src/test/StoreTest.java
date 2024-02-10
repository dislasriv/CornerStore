import model.Player;
import model.Store;
import model.products.Orange;
import model.products.OrangeJuice;
import model.products.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
Class that tests the behaviour of store
 */
public class StoreTest {

    private Store testStore;
    private ArrayList<Product> defaultOps;
    private ArrayList<Product> defaultUns;
    private Player testPlr;

    @BeforeEach
    void setup() {
        testPlr = new Player();
        testStore = new Store(testPlr);
        defaultOps = new ArrayList<Product>();
        defaultUns = new ArrayList<Product>();


        defaultOps.add(new Orange());
        defaultUns.add(new OrangeJuice());
    }

    //testConstructor
    @Test
    void testConstructor() {
        assertEquals("Orange", testStore.getAvailibleOptions().get(0).getName());
        assertEquals("Orange Juice", testStore.getAvailibleUnlocks().get(0).getName());
        assertTrue(testPlr.equals(testStore.getPlr()));
    }

    //buyProduct
    @Test
    void testBuyProductNotAvailable() {
        assertEquals(Store.DNE, testStore.buyProduct("mooga"));
    }

    @Test
    void testBuyProductAvailableTooPoor() {
        testPlr.modifyMoney(-100000);
        assertEquals(Store.CANT_AFFORD, testStore.buyProduct("oRanGE"));
        assertEquals(Store.CANT_AFFORD, testStore.buyProduct("oraNge"));
    }

    @Test
    void testBuyProductAvailableCanAfford() {
        assertEquals(Store.SUCCESSFUL_BUY + "Orange.", testStore.buyProduct("ORange"));
        assertEquals("Orange", testPlr.getInventory().getProducts().get(0).getName());
        assertEquals(testPlr.INITIAL_MONEY -testPlr.getInventory().getProducts().get(0).getCost(),
                testPlr.getMoney());
    }

    //unlockProduct
    @Test
    void testUnlockProductNotFound() {
        assertEquals(Store.DNE, testStore.unlockProduct("mooga"));
    }


    @Test
    void testUnlockProductTooLowLevel() {
        assertEquals(Store.TOO_LOW_LEVEL + OrangeJuice.LVL_REQ + ".",
                testStore.unlockProduct("OrANge Juice"));
    }

    @Test
    void testUnlockProductTooPoor() {
        testPlr.modifyMoney(-100000);
        testPlr.gainExp(1000);
        testPlr.checkLevelUp();
        assertEquals(Store.CANT_AFFORD, testStore.unlockProduct("OrANge Juice"));
        assertEquals(Store.CANT_AFFORD, testStore.unlockProduct("OrANge Juice"));
    }

    @Test
    void testUnlockProductAllNormal() {
        //setup
        testPlr.modifyMoney(OrangeJuice.UNLOCK_COST);
        int cmoney = testPlr.getMoney();
        int clength = testStore.getAvailibleOptions().size();

        testPlr.gainExp(1000);
        testPlr.checkLevelUp();
        assertEquals(Store.SUCCESSFUL_UNLOCK + OrangeJuice.NAME + ".",
                testStore.unlockProduct("OrANge Juice"));

        assertEquals(cmoney - OrangeJuice.UNLOCK_COST, testPlr.getMoney());
        assertEquals(clength + 1, testStore.getAvailibleOptions().size());
    }
}
