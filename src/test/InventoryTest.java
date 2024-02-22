
import model.Inventory;
import model.products.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 tests for the inventory class
 */
public class InventoryTest {

    private Inventory testInv;
    private Product p1;
    private Product p2;
    private Product p3;
    private ArrayList<Product> testList;

    @BeforeEach
    void setup() {
        testInv = new Inventory();
        p1 = new Product("Gun", 100, 2, 120, 4, 20, 10);
        p2 = new Product("Bur", 10, 1, 21, 1, 4, 1);
        testList = new ArrayList<Product>();

        testList.add(p1);
        testList.add(p2);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testInv.getSize());
    }

    //addProduct
    @Test
    void testAddProductOnce() {
        testInv.addProduct(p1);
        assertEquals(p1, testInv.getProducts().get(0));
    }

    @Test
    void testAddProductMulti() {
        testInv.addProduct(p1);
        assertEquals(p1, testInv.getProducts().get(0));
        testInv.addProduct(p2);
        assertEquals(p2, testInv.getProducts().get(1));
        testInv.addProduct(p1);
        assertEquals(p1, testInv.getProducts().get(2));
    }

    //removeProduct
    @Test
    void testRemoveProductOnce() {
        testInv.addProduct(p1);
        testInv.addProduct(p2);

        assertEquals(2, testInv.getSize());
        assertEquals(testList, testInv.getProducts());

        //testList addition order is same
        testInv.removeProduct(1);
        testList.remove(1);
        assertEquals(1, testInv.getSize());
        assertEquals(testList, testInv.getProducts());
    }

    @Test
    void testRemoveProductMultiTooSmall() {
        testInv.removeProduct(3);
        assertEquals(0, testInv.getSize());

        testInv.addProduct(p1);
        testInv.addProduct(p2);

        //testList addition order is same
        assertEquals(2, testInv.getSize());
        assertEquals(testList, testInv.getProducts());

        testInv.removeProduct(1);
        testList.remove(1);
        assertEquals(1, testInv.getSize());
        assertEquals(testList, testInv.getProducts());

        testInv.removeProduct(0);
        testList.remove(0);
        assertEquals(0, testInv.getSize());
        assertEquals(testList, testInv.getProducts());
    }

    //dropCheck
    @Test
    void testDropCheckEmpty() {
       assertEquals(0, testInv.dropCheck());
    }

    @Test
    void testDropCheckNoDrop() {
        testInv.addProduct(p1);
        testInv.addProduct(p2);
        assertEquals(0, testInv.dropCheck());
    }

    @Test
    void testDropCheckMixed() {
        testInv.addProduct(p1);
        testInv.addProduct(p2);

        for(int i = 0; i < 20; i++){
            p2.increaseTimeInStore();
        }
        assertEquals(1, testInv.dropCheck());
    }

    //getters
    @Test
    void testGetters() {
        assertEquals(new ArrayList<Product>(), testInv.getProducts());
        assertEquals(0, testInv.getSize());

    }
}
