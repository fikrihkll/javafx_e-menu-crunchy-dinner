package data.model;

public class Food {

    public Food(int id, String name, int price, String pict){
        this.id= id;
        this.name= name;
        this.price = price;
        this.pict = pict;
    }

    private int id ;
    private String name;
    private int price;
    private String pict;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPict() {
        return pict;
    }

    public void setPict(String pict) {
        this.pict = pict;
    }
}
