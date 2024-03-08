package model.products;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrangeSorbetTest {

    private OrangeSorbet sorbet;

    @BeforeEach
    void setup() {
        sorbet = new OrangeSorbet();
    }

    @Test
    void testConstructor() {
        assertEquals(sorbet.getName(), "Orange Sorbet");
        assertEquals(sorbet.getCost(), OrangeSorbet.COST);
        assertEquals(sorbet.getSalePrice(), OrangeSorbet.SALE_PRICE);
        assertEquals(sorbet.getLvlReq(), OrangeSorbet.LVL_REQ);
        assertEquals(sorbet.getExpValue(), OrangeSorbet.EXP_VALUE);
        assertEquals(sorbet.getTimeInStore(), 0);
    }
}
