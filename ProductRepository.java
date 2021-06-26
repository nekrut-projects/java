package lesson_2;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ProductRepository {
    List<Product> products;

    @PostConstruct
    public void init(){
        products = new ArrayList<>();

        Random random = new Random();

        for (int i = 1; i <= 5; i++) {
            products.add(new Product("Product - " + i,
                    i, random.nextFloat() * 1000));
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProductById(int id){
        for (Product p : products) {
            if (id == p.getId()) {
                return p;
            }
        }
        return null;
    }
}
