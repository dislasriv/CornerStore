package persistence;

import model.Player;
import model.Store;
import model.products.Orange;
import model.products.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

/*
Class that tests that saving works on a simulated app state.
 */

public class SaveWriterTest {

    private static final String TEST_DEST = "data/TestWriting.json";
    private SaveWriter testWriter;

    //app fields

    private Store testStore;
    private int testDay;

    @BeforeEach
    void setup() {
        try {
            testWriter = new SaveWriter(TEST_DEST);
            testStore = new Store(new Player());
            testDay = 0;
        } catch (FileNotFoundException e) {
           fail("setup malformed");
        }


    }

    //testConstructor
    @Test
    void testConstructorWorks() {
        assertEquals(TEST_DEST, testWriter.getDest());
    }

    @Test
    void testConstructorFails() {
        try {
            SaveWriter badTest = new SaveWriter("I am going to kill myself");
        } catch (FileNotFoundException e) {
            //pass
        }
    }

    //testWriteData
    @Test
    void testWriteDataDefault() {
        //opened when constructed
        testWriter.writeData(testStore, testDay);
        testWriter.getWriter().close();

        try {
            SaveLoader testLoader = new SaveLoader(TEST_DEST);

            assertEquals(testStore.toJson().toString(), testLoader.parseStore().toJson().toString());
            assertEquals(testDay, testLoader.parseDay());
        } catch (IOException e) {
            fail("Destination given malformed...check constant");
        }
    }

    @Test
    void testWriteDataGeneral() {
        //opened when constructed
        Store testStore2 = new Store(new Player());

        //augment player
        testStore2.getPlr().setExp(912);
        testStore2.getPlr().setMoney(213);
        testStore2.getPlr().setLevel(11);
        testStore2.getPlr().getInventory().addProduct(new Orange());

        //augment store
        testStore2.getAvailableOptions().add(
                new Product("moo", 12 , 3, 2, 3,4,5));

        testWriter.writeData(testStore2, 900);
        testWriter.getWriter().close();

        try {
            SaveLoader testLoader = new SaveLoader(TEST_DEST);

            assertEquals(testStore2.toJson().toString(), testLoader.parseStore().toJson().toString());
            assertEquals(900, testLoader.parseDay());
        } catch (IOException e) {
            fail("Destination given malformed...check constant");
        }

        //testWriteDataDefault();
        //I don't think you can write to the same file twice in a method?
    }
}
