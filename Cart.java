package lesson_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    private ProductRepository productRepository;
    private List<Product> selectedProducts;

    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }

    public boolean addProduct(int id){
        Product product = getProductById(id);
        if (product != null) {
            selectedProducts.add(product);
        }
        return false;
    }

    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        this.selectedProducts = new ArrayList<>();
    }

    public void deleteProductById(int id) {
        Product product = getProductById(id);
        if (product != null) {
            selectedProducts.remove(product);
        }
    }
}
