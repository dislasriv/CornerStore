package model.products;


/*
A particular instance of the Product class with specific stats.
 */
public class OrangeGum extends Product {

    public static final String NAME = "Orange Gum";
    public static final int COST = 4;
    public static final int EXPIRY = 2;
    public static final int SALE_PRICE = 9;
    public static final int LVL_REQ = 0;
    public static final int EXP_VALUE = 3;
    public static final int UNLOCK_COST = 0;

    //EFFECTS: Creates a new orange product.
    public OrangeGum() {
        super(NAME, COST, EXPIRY, SALE_PRICE, LVL_REQ, EXP_VALUE, UNLOCK_COST);
    }
}
