import model.Inventory;
import model.Player;
import model.products.Orange;
import model.products.OrangeJuice;
import model.products.Product;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/*
class that tests the behaviour of the player.
 */
public class PlayerTest {

    private Player testPlr;

    @BeforeEach
    void setup() {
        testPlr = new Player();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testPlr.getLevel());
        assertEquals(Player.INITIAL_MONEY, testPlr.getMoney());
        assertEquals(0, testPlr.getInventory().getSize());
        assertEquals(1, testPlr.getExp());
    }

    //checkLevelUp
    @Test
    void testCheckLevelUpOnceFail() {
        assertFalse(testPlr.checkLevelUp());
        assertEquals(0, testPlr.getLevel());
    }

    @Test
    void testCheckLevelUpMultiFailThenPass() {
        assertFalse(testPlr.checkLevelUp());
        assertEquals(0, testPlr.getLevel());

        testPlr.gainExp(3);
        assertTrue(testPlr.checkLevelUp());
        assertEquals(1, testPlr.getLevel());
        assertFalse(testPlr.checkLevelUp());
    }

    //modifyMoney
    @Test
    void modifyMoneyOncePos() {
        testPlr.modifyMoney(100);
        assertEquals(100 + Player.INITIAL_MONEY, testPlr.getMoney());
    }

    @Test
    void modifyMoneyOnceMultiPosNeg() {
        testPlr.modifyMoney(25);
        assertEquals(25 + Player.INITIAL_MONEY, testPlr.getMoney());
        testPlr.modifyMoney(-20);
        assertEquals(5 + Player.INITIAL_MONEY, testPlr.getMoney());
    }

    //sellProduct
    @Test
    void testSellProductOnce() {
        //setup
        testPlr.addItemToInventory(new Orange());
        int price = testPlr.getInventory().getProducts().get(0).getSalePrice();

        testPlr.sellProduct(0);
        assertEquals(0, testPlr.getInventory().getSize());
        assertEquals(Player.INITIAL_MONEY + price,
                testPlr.getMoney());
    }

    @Test
    void testSellProductMulti() {
        testPlr.addItemToInventory(new Orange());
        testPlr.addItemToInventory(new Orange());
        int price = testPlr.getInventory().getProducts().get(0).getSalePrice();

        testPlr.sellProduct(1);
        assertEquals(1, testPlr.getInventory().getSize());
        assertEquals(Player.INITIAL_MONEY + price,
                testPlr.getMoney());

        testPlr.sellProduct(0);
        assertEquals(0, testPlr.getInventory().getSize());
        assertEquals(Player.INITIAL_MONEY + (2 * price),
                testPlr.getMoney());
    }

    //gainExp
    @Test
    void testGainExpOnce() {
        assertEquals(1, testPlr.getExp());
        testPlr.gainExp(3);
        assertEquals(4, testPlr.getExp());
    }

    @Test
    void testGainExpMulti() {
        assertEquals(1, testPlr.getExp());
        testPlr.gainExp(9);
        assertEquals(10, testPlr.getExp());
        testPlr.gainExp(10);
        assertEquals(20, testPlr.getExp());
    }

    //addItemToInventory
    @Test
    void addItemToInventoryOnce() {
        Product oj = new OrangeJuice();
        testPlr.addItemToInventory(oj);
        assertEquals(oj, testPlr.getInventory().getProducts().get(0));
    }

    @Test
    void addItemToInventoryMulti() {

        Product oj = new OrangeJuice();
        Product og = new Orange();

        testPlr.addItemToInventory(oj);
        assertEquals(oj, testPlr.getInventory().getProducts().get(0));
        testPlr.addItemToInventory(og);
        assertEquals(og, testPlr.getInventory().getProducts().get(1));
    }

    //toString
    @Test
    void testToStringOnce() {
        assertEquals("You are level " + testPlr.getLevel() + " and have $" + Player.INITIAL_MONEY + ".",
                testPlr.toString());
    }

    @Test
    void testToStringMultiAndProgress() {
        assertEquals("You are level 0 and have $" + Player.INITIAL_MONEY + ".",
                testPlr.toString());
        testPlr.gainExp(9);
        testPlr.checkLevelUp();
        testPlr.modifyMoney(25);
        assertEquals("You are level 2 and have $" + (Player.INITIAL_MONEY + 25 + "."),
                testPlr.toString());
    }

    //toJson
    @Test
    void testToJson() {
        JSONObject plrData = testPlr.toJson();

        assertEquals(plrData.get("level"), plrData.get("level"));
        assertEquals(plrData.get("money"), plrData.get("money"));
        assertEquals(plrData.get("exp"), plrData.get("exp"));
        assertEquals(plrData.get("inv").toString(), plrData.get("inv").toString());
    }

    @Test
    void testToJsonMulti() {
        Player newTestPlr = new Player();
        newTestPlr.setMoney(69);
        newTestPlr.setExp(40);
        newTestPlr.setLevel(90000);
        newTestPlr.getInventory().addProduct(new Orange());

        JSONObject plrData = newTestPlr.toJson();
        assertEquals(plrData.get("level"), plrData.get("level"));
        assertEquals(plrData.get("money"), plrData.get("money"));
        assertEquals(plrData.get("exp"), plrData.get("exp"));
        assertEquals(plrData.get("inv").toString(), plrData.get("inv").toString());

        testToJson();
    }

    //getters
    @Test
    void testGetters() {
        assertEquals(0, testPlr.getLevel());
        assertEquals(Player.INITIAL_MONEY, testPlr.getMoney());
        assertEquals(1, testPlr.getExp());
        assertEquals(0, testPlr.getInventory().getSize());
    }
}
