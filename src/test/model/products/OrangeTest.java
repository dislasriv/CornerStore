package model.products;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 tests for the orange class.
 */
public class OrangeTest {

    private Orange orange;

    @BeforeEach
    void setup() {
        orange = new Orange();
    }

    @Test
    void testConstructor() {
        assertEquals(orange.getName(), "Orange");
        assertEquals(orange.getCost(), Orange.COST);
        assertEquals(orange.getSalePrice(), Orange.SALE_PRICE);
        assertEquals(orange.getLvlReq(), Orange.LVL_REQ);
        assertEquals(orange.getExpValue(), Orange.EXP_VALUE);
        assertEquals(orange.getTimeInStore(), 0);
    }
}
