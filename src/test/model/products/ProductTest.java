package model.products;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Math.floor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/*
 tests for the Product class.
 */
public class ProductTest {

    protected Product testp;
    protected Product p1;
    protected Product p2;

    @BeforeEach
    public void setup() {
        testp = new Product("Sock", 3, 2, 4, 0, 1, 100);
        p1 = new Product("Gun", 100, 2, 120, 4, 20, 10);
        p2 = new Product("Bur", 10, 1, 21, 1, 4, 1);
    }

    @Test
    public void testConstructor() {
        assertEquals(testp.getName(), "Sock");
        assertEquals(testp.getCost(), 3);
        assertEquals(testp.getSalePrice(), 4);
        assertEquals(testp.getLvlReq(), 0);
        assertEquals(testp.getExpValue(), 1);
        assertEquals(testp.getTimeInStore(), 0);
        assertEquals(testp.getUnlockCost(), 100);
    }

    //putOnClearance
    @Test
    void testPutOnClearanceOnceRemainsSame() {
        //call
        testp.putOnClearance();
        assertEquals(4, testp.getSalePrice());
    }

    @Test
    void testPutOnClearanceMultiItemsMixedChangesEqualExpiry() {
        //call
        p1.putOnClearance();
        assertEquals(120, p1.getSalePrice());

        //see how truncation works
        p2.increaseTimeInStore();
        p2.putOnClearance();
        assertEquals(floor(21-21*.25), p2.getSalePrice());
    }

    @Test
    void testPutOnClearanceSameItemMultiMixedGreaterThanExpiryAlrCleared() {
        //call
        p1.putOnClearance();
        assertEquals(120, p1.getSalePrice());
        p1.increaseTimeInStore();
        p1.increaseTimeInStore();
        p1.increaseTimeInStore();
        p1.putOnClearance();
        assertEquals(floor(120 - 120*.25), p1.getSalePrice());
        p1.putOnClearance();
        assertEquals(floor(120 - 120*.25), p1.getSalePrice());
    }

    //increaseTimeInStore
    @Test
    void testIncreaseTimeInStoreOnce() {
        //call
        testp.increaseTimeInStore();
        assertEquals(1, testp.getTimeInStore());
    }

    @Test
    void testIncreaseTimeInStoreMulti() {
        //call
        testp.increaseTimeInStore();
        assertEquals(1, testp.getTimeInStore());
        testp.increaseTimeInStore();
        assertEquals(2, testp.getTimeInStore());

        assertEquals(0, p1.getTimeInStore());
        p1.increaseTimeInStore();
        assertEquals(1, p1.getTimeInStore());
    }

    //toString
    @Test
    void testIncreaseToStringOnce() {
        //call
        assertEquals("A Sock it costs $3 to buy and sells for $4, it goes on clearance 2 days after you buy it."
               + " It gives you 1 exp when you sell it.", testp.toString());
    }

    @Test
    void testIncreaseToStringMulti() {
        //call
        assertEquals("A Sock it costs $3 to buy and sells for $4," +
                " it goes on clearance 2 days after you buy it."
               + " It gives you 1 exp when you sell it." , testp.toString());
        assertEquals("A Gun it costs $100 to buy and sells for $120, it goes on " +
                "clearance 2 days after you buy it."
                + " It gives you 20 exp when you sell it.", p1.toString());
    }


    //clone
    @Test
    void testClone() {
        //call
        testp = testp.clone();
        assertEquals(testp.getName(), "Sock");
        assertEquals(testp.getCost(), 3);
        assertEquals(testp.getSalePrice(), 4);
        assertEquals(testp.getLvlReq(), 0);
        assertEquals(testp.getExpValue(), 1);
        assertEquals(testp.getTimeInStore(), 0);
        assertEquals(testp.getUnlockCost(), 100);
    }

    //toJson
    @Test
    void testToJson() {
        Orange orange = new Orange();
        JSONObject productData = orange.toJson();

        //compare fields
        assertEquals(productData.getString("name"), orange.getName());
        assertEquals(productData.getInt("timeInStore"), orange.getTimeInStore());
        assertEquals(productData.getInt("cost"), orange.getCost());
        assertEquals(productData.getInt("expiryDate"), orange.getExpiryDate());
        assertEquals(productData.getInt("salePrice"), orange.getSalePrice());
        assertEquals(productData.getInt("lvlReq"), orange.getLvlReq());
        assertEquals(productData.getInt("expValue"), orange.getExpValue());
        assertEquals(productData.getInt("unlockCost"), orange.getUnlockCost());
        assertEquals(productData.getBoolean("clearance"), orange.onClearance());
    }
    @Test
    void testToJsonMulti() {
        OrangeJuice juice = new OrangeJuice();
        JSONObject productData = juice.toJson();
        //compare fields
        assertEquals(productData.getString("name"), juice.getName());
        assertEquals(productData.getInt("timeInStore"), juice.getTimeInStore());
        assertEquals(productData.getInt("cost"), juice.getCost());
        assertEquals(productData.getInt("expiryDate"), juice.getExpiryDate());
        assertEquals(productData.getInt("salePrice"), juice.getSalePrice());
        assertEquals(productData.getInt("lvlReq"), juice.getLvlReq());
        assertEquals(productData.getInt("expValue"), juice.getExpValue());
        assertEquals(productData.getInt("unlockCost"), juice.getUnlockCost());
        assertEquals(productData.getBoolean("clearance"), juice.onClearance());
        testToJson();
    }

    //getters
    @Test
    void testGetters() {
        assertEquals("Sock", testp.getName());
        assertEquals(3, testp.getCost());
        assertEquals(4, testp.getSalePrice());
        assertEquals(0, testp.getLvlReq());
        assertEquals(1, testp.getExpValue());
        assertEquals(0, testp.getTimeInStore());
        assertFalse(testp.onClearance());
    }
}
