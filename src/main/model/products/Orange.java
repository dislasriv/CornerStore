package model.products;

/*
A particular instance of the Product class with specific stats.
 */
public class Orange extends Product {
    public static final String NAME = "Orange";
    public static final int COST = 1;
    public static final int EXPIRY = 2;
    public static final int SALE_PRICE = 5;
    public static final int LVL_REQ = 0;
    public static final int EXP_VALUE = 2;
    public static final int UNLOCK_COST = 0;

    public Orange() {
        super(NAME, COST, EXPIRY, SALE_PRICE, LVL_REQ, EXP_VALUE, UNLOCK_COST);
    }
}
