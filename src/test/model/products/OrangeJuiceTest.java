package model.products;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 tests for the orange juice class.
 */
public class OrangeJuiceTest {

    private OrangeJuice oj;

    @BeforeEach
    void setup() {
        oj = new OrangeJuice();
    }

    @Test
    void testConstructor() {
        assertEquals(oj.getName(), "Orange Juice");
        assertEquals(oj.getCost(), OrangeJuice.COST);
        assertEquals(oj.getSalePrice(), OrangeJuice.SALE_PRICE);
        assertEquals(oj.getLvlReq(), OrangeJuice.LVL_REQ);
        assertEquals(oj.getExpValue(), OrangeJuice.EXP_VALUE);
        assertEquals(oj.getTimeInStore(), 0);
    }
}