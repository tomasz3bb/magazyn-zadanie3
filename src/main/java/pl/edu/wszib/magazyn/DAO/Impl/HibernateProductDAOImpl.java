package pl.edu.wszib.magazyn.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.magazyn.DAO.IProductDAO;
import pl.edu.wszib.magazyn.model.Product;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class HibernateProductDAOImpl implements IProductDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Product getProductById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Product> query = session.createQuery("FROM pl.edu.wszib.magazyn.model.Product WHERE id = :id");
        query.setParameter("id", id);
        Product product = null;
        try {
            product = query.getSingleResult();
        } catch (NoResultException e) {
            if (product != null){
                System.out.println("Nie znaleziono produktu");
            }
        }
        session.close();
        return product;
    }

    @Override
    public void updateProduct(Product product) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(product);
            tx.commit();
        } catch (Exception e) {
            if (tx != null){
                tx.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public void addQuantity(int id) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        Query<Product> query = session.createQuery("FROM pl.edu.wszib.magazyn.model.Product WHERE id = :id");
        query.setParameter("id", id);
        Product product = null;
        try {
            product = query.getSingleResult();
            tx = session.beginTransaction();
            product.setQuantity(product.getQuantity() + 1);
            session.update(product);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (product != null){
                System.out.println("Nie znaleziono produktu");
            }
        }
        finally {
            session.close();
        }
    }

    @Override
    public void deleteQuantity(int id) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        Query<Product> query = session.createQuery("FROM pl.edu.wszib.magazyn.model.Product WHERE id = :id");
        query.setParameter("id", id);
        Product product = null;
        try {
            product = query.getSingleResult();
            tx = session.beginTransaction();
            product.setQuantity(product.getQuantity() - 1);
            session.update(product);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (product != null){
                System.out.println("Nie znaleziono produktu");
            }
        }
        finally {
            session.close();
        }
    }

    @Override
    public boolean addProduct(Product product) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(product);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }finally {
            session.close();
        }
        return true;
    }

    @Override
    public void deleteProduct(Product product) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(product);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public List<Product> getAllProducts() {
        Session session = this.sessionFactory.openSession();
        Query<Product> query = session.createQuery("FROM pl.edu.wszib.magazyn.model.Product");
        List<Product> products = query.getResultList();
        session.close();
        return products;
    }
}
