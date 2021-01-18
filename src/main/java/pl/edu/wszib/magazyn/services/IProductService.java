package pl.edu.wszib.magazyn.services;

import pl.edu.wszib.magazyn.model.Product;
import pl.edu.wszib.magazyn.model.view.ProductModel;

import java.util.List;

public interface IProductService {
    Product getProductById(int id);
    void updateProduct(Product product);
    boolean addProduct(ProductModel productModel);
    void deleteProduct(Product product);
    List <Product> getAllProducts();

}
