package pl.edu.wszib.magazyn.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.magazyn.DAO.IProductDAO;
import pl.edu.wszib.magazyn.model.Product;
import pl.edu.wszib.magazyn.model.view.ProductModel;
import pl.edu.wszib.magazyn.services.IProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    IProductDAO productDAO;

    @Override
    public Product getProductById(int id) {
        return this.productDAO.getProductById(id);
    }

    @Override
    public void updateProduct(Product product) {
        Product productFromDB = this.productDAO.getProductById(product.getId());
        productFromDB.setCategory(product.getCategory());
        productFromDB.setName(product.getName());
        productFromDB.setQuantity(product.getQuantity());
        productFromDB.setCode(product.getCode());
        productFromDB.setPrice(product.getPrice());

        this.productDAO.updateProduct(productFromDB);

    }

    @Override
    public boolean addProduct(ProductModel productModel) {
        Product newProduct = new Product(0, productModel.getCategory(), productModel.getName(),
                productModel.getCode(), productModel.getQuantity(), productModel.getPrice());
        return this.productDAO.addProduct(newProduct);
    }
    @Override
    public void deleteProduct(Product product) {
        Product productFromDatabase = this.productDAO.getProductById(product.getId());
        this.productDAO.deleteProduct(productFromDatabase);
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productDAO.getAllProducts();
    }
}
