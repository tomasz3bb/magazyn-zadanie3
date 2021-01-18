package pl.edu.wszib.magazyn.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.magazyn.DAO.IUserDAO;
import pl.edu.wszib.magazyn.model.User;

import javax.persistence.NoResultException;

@Repository
public class HibernateUserDAOImpl implements IUserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User getUserByLogin(String login) {
        Session session = this.sessionFactory.openSession();
        Query<User> query =session.createQuery("FROM pl.edu.wszib.magazyn.model.User WHERE login = :login");
        query.setParameter("login", login);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
            if (user != null){
                System.out.println("Nie znaleziono usera!");
            }
        }
        session.close();
        return user;
    }

    @Override
    public boolean persistUser(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null){
                tx.rollback();
            }
        }
        finally {
            session.close();
        }
        return false;
    }
}
