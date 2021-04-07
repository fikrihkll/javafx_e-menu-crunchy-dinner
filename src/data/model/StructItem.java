package data.model;

public class StructItem {

    public StructItem(String name, int price, int qty) {
        this.name = name;
        this.price = price;
        this.qty= qty;
    }

    private String name;
    private int price;
    private int qty;

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
