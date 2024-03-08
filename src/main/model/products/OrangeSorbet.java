package model.products;

public class OrangeSorbet extends Product {
    public static final String NAME = "Orange Sorbet";
    public static final int COST = 8;
    public static final int EXPIRY = 4;
    public static final int SALE_PRICE = 19;
    public static final int LVL_REQ = 2;
    public static final int EXP_VALUE = 5;
    public static final int UNLOCK_COST = 100;

    //EFFECTS: Creates a new orange product.
    public OrangeSorbet() {
        super(NAME, COST, EXPIRY, SALE_PRICE, LVL_REQ, EXP_VALUE, UNLOCK_COST);
    }
}
