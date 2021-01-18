package pl.edu.wszib.magazyn.database;

import pl.edu.wszib.magazyn.model.Product;

import java.util.List;

public interface IProductsRepository {
    List<Product> getAllProducts();
    boolean addProductQuantity(String code, int quantity);
    boolean deleteProductQuantity(String code, int quantity);
    Product getProductByCode(String code);
}
