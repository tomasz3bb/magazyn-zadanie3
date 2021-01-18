package pl.edu.wszib.magazyn.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.magazyn.DAO.IProductDAO;
import pl.edu.wszib.magazyn.services.IStorageService;

@Service
public class StorageServiceImpl implements IStorageService {

    @Autowired
    IProductDAO productDAO;

    @Override
    public void addProductByIdToStorage(int id) {
        this.productDAO.addQuantity(id);
    }

    @Override
    public void deleteProductByIdFromStorage(int id) {
        if (this.productDAO.getProductById(id).getQuantity()>0){
            this.productDAO.deleteQuantity(id);
        }
    }
}
