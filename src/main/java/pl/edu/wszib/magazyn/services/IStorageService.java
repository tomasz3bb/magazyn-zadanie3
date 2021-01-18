package pl.edu.wszib.magazyn.services;

public interface IStorageService {
    void addProductByIdToStorage(int id);
    void deleteProductByIdFromStorage(int id);
}
