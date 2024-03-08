package model.products;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrangeGumTest {

    private OrangeGum orangeGum;

    @BeforeEach
    void setup() {
        orangeGum = new OrangeGum();
    }

    @Test
    void testConstructor() {
        assertEquals(orangeGum.getName(), "Orange Gum");
        assertEquals(orangeGum.getCost(), OrangeGum.COST);
        assertEquals(orangeGum.getSalePrice(), OrangeGum.SALE_PRICE);
        assertEquals(orangeGum.getLvlReq(), OrangeGum.LVL_REQ);
        assertEquals(orangeGum.getExpValue(), OrangeGum.EXP_VALUE);
        assertEquals(orangeGum.getTimeInStore(), 0);
    }
}
