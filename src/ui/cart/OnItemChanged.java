package ui.cart;



public interface OnItemChanged {
    void onItemRemoved(int cartId);
    void onQtyChanged(int newQty,int cartId);
}
