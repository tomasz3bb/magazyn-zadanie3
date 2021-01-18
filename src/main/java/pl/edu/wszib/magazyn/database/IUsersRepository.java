package pl.edu.wszib.magazyn.database;

import pl.edu.wszib.magazyn.model.User;

public interface IUsersRepository {
    User authenticate(User user);
    boolean register (User user);
}
