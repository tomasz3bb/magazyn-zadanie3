package pl.edu.wszib.magazyn.DAO.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.magazyn.DAO.IUserDAO;
import pl.edu.wszib.magazyn.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAOImpl implements IUserDAO {

    @Autowired
    Connection connection;

    @Override
    public User getUserByLogin(String login) {
        String sql = "SELECT * FROM tuser WHERE login = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                User userFromDB = new User();
                userFromDB.setId(resultSet.getInt("id"));
                userFromDB.setLogin(resultSet.getString("login"));
                userFromDB.setPass(resultSet.getString("pass"));
                userFromDB.setRole(User.Role.valueOf(resultSet.getString("role")));

                return userFromDB;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean persistUser(User user) {
        String sql = "INSERT INTO tuser (login, pass, role) VALUES (?, ?, ?);";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getLogin() );
            preparedStatement.setString(2, user.getPass());
            preparedStatement.setString(3, user.getRole().toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
