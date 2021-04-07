package data.model;

public class FoodCart extends Food {

    public FoodCart(int id, String name, int price, String pict, int qty) {
        super(id, name, price, pict);
        this.cartId = cartIdStatic;
        cartIdStatic++;
        this.qty = qty;
    }

    public static int cartIdStatic=0;

    private int cartId;

    private int qty;

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }
}
