package pl.edu.wszib.magazyn.database.impl;

import org.springframework.stereotype.Component;
import pl.edu.wszib.magazyn.database.IUsersRepository;
import pl.edu.wszib.magazyn.model.User;

import java.util.ArrayList;
import java.util.List;


public class UsersRepositoryImpl implements IUsersRepository {
    private final List<User> users = new ArrayList<>();

    public UsersRepositoryImpl() {
        this.users.add(new User(1,"admin", "admin", User.Role.ADMIN));
    }

    @Override
    public User authenticate(User user) {
        for(User userFormDatabase : this.users) {
            if(userFormDatabase.getLogin().equals(user.getLogin())
                    && userFormDatabase.getPass().equals(user.getPass())) {
                return userFormDatabase;
            }
        }
        return null;
    }

    @Override
    public boolean register(User user) {
        if(isLoginInDB(user.getLogin())){
            return false;
        }
        this.users.add(user);
        return true;
    }

    private boolean isLoginInDB(String login){
        for (User user : users){
            if (user.getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }
}
