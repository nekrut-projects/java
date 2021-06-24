package lesson_1;

public class Product {
    private String title;
    private int id;
    private float cost;

    public Product(String title, int id, float cost) {
        this.title = title;
        this.id = id;
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
