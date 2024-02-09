package model.products;

public class OrangeJuice extends Product {
    public static final String NAME = "Orange Juice";
    public static final int COST = 7;
    public static final int EXPIRY = 2;
    public static final int SALE_PRICE = 15;
    public static final int LVL_REQ = 1;
    public static final int EXP_VALUE = 2;
    public static final int UNLOCK_COST = 50;

    public OrangeJuice() {
        super(NAME, COST, EXPIRY, SALE_PRICE, LVL_REQ, EXP_VALUE, UNLOCK_COST);
    }
}
