package persistence;

import model.Player;
import model.Store;
import model.products.Product;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

/*
Class that tests that loading works on dummy data.
 */
public class SaveLoaderTest {

    public static final String DATA_SOURCE = "data/TestForSaveLoader.json";
    public static final String EMPTY_SOURCE = "data/DefaultStats.json";
    private SaveLoader testSave;
    private JSONObject data;

    @BeforeEach
    void setup() {
        try {
            testSave = new SaveLoader(DATA_SOURCE);
            data = testSave.getData();
        } catch (IOException e) {
           fail();
        }
    }

    //Test Constructor
    @Test
    void testConstructorLegitSource() {
        try {
            //Get json object formed from string that cam out of readFile in constructor
            JSONObject testJsonObj = new JSONObject(testSave.readFile(DATA_SOURCE));
            assertEquals(DATA_SOURCE, testSave.getSource());
            assertEquals(testJsonObj.get("day"), data.get("day"));


            //some player checks
            JSONObject plrJson = ((JSONObject)((JSONObject)testJsonObj.get("store")).get("plr"));
            JSONObject dataPlr = ((JSONObject)((JSONObject)data.get("store")).get("plr"));
            assertEquals(plrJson.get("level"),
                    (dataPlr.get("level")));

            assertEquals(plrJson.get("money"),
                    (dataPlr.get("money")));

            assertEquals(plrJson.get("exp"),
                    (dataPlr.get("exp")));

            //store checks
            JSONObject storeJson = (JSONObject)testJsonObj.get("store");
            JSONObject dataStore = (JSONObject)data.get("store");
            assertEquals(storeJson.getJSONArray("availableOptions").getJSONObject(0).get("name"),
                    storeJson.getJSONArray("availableOptions").getJSONObject(0).get("name"));
        } catch (IOException e) {
           fail("bad file source");
        }
    }

    //Potentially redundant
    @Test
    void testConstructorDefaultSource() {
        try {
            testSave = new SaveLoader(EMPTY_SOURCE);
            //Get json object formed from string that cam out of readFile in constructor
            JSONObject testJsonObj = new JSONObject(testSave.readFile(EMPTY_SOURCE));
            assertEquals(EMPTY_SOURCE, testSave.getSource());
            assertEquals(testJsonObj.get("day"), testSave.getData().get("day"));
            assertEquals(((JSONObject)testJsonObj.get("plr")).get("level"),
                    ((JSONObject)testSave.getData().get("plr")).get("level"));
            assertEquals(((JSONObject)testJsonObj.get("plr")).get("money"),
                    ((JSONObject)testSave.getData().get("plr")).get("money"));

        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testConstructorBadSource() {
        try {
            JSONObject testJsonObj = new JSONObject(testSave.readFile("FART"));
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    //test ParseStore and Player
    @Test
    void testParseStoreAndPlayer () {
        Store testStore = testSave.parseStore();
        assertEquals("Orange", testStore.getAvailibleOptions().get(0).getName());
        assertEquals("Orange Juice", testStore.getAvailibleOptions().get(1).getName());
        assertEquals(0, testStore.getAvailibleUnlocks().size());
        testParsePlayer(testStore.getPlr());
    }
    void testParsePlayer (Player testPlr) {
        assertEquals(13, testPlr.getLevel());
        assertEquals(6900, testPlr.getMoney());
        assertEquals(1, testPlr.getInventory().getSize());
        assertEquals(900, testPlr.getExp());
    }

    //test parseLop
    @Test
    void testParseListOfProduct () {
        //get available options in JSONArray format
        JSONArray arrayData = (JSONArray) ((JSONObject) data.get("store")).get("availableOptions");
        //attempt to parse
        ArrayList<Product> availableOptions = testSave.parseListOfProduct(arrayData);
        assertEquals(2, availableOptions.size());
        assertEquals("Orange", availableOptions.get(0).getName());
        assertEquals("Orange Juice", availableOptions.get(1).getName());
    }



    //test parseDay
    @Test
    void testParseDay() {
        assertEquals(96, testSave.parseDay());
    }

}
