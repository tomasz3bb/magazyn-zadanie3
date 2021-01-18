package pl.edu.wszib.magazyn.DAO;

import pl.edu.wszib.magazyn.model.User;

public interface IUserDAO {
    User getUserByLogin(String login);
    boolean persistUser(User user);
}
