package pl.edu.wszib.magazyn.DAO;

import pl.edu.wszib.magazyn.model.Product;

import java.util.List;

public interface IProductDAO {
    Product getProductById(int id);
    void updateProduct(Product product);
    void addQuantity(int id);
    void deleteQuantity(int id);
    boolean addProduct(Product product);
    void deleteProduct(Product product);
    List<Product> getAllProducts();
}
