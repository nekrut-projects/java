package lesson_2;

public class Product {
    private String name;
    private int id;
    private float price;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public Product(String name, int id, float price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }
}
