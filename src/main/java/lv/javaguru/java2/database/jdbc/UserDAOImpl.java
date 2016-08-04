package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends DAOImpl{

    public void createUserWithId(User user) throws DBException {
        if(user == null)return;
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO java2miska.user (user_fullName, user_email, user_password) values(?,?,?)",
                            PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                user.setId(resultSet.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.createUserWithId()");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public long createUserReturnId(User user) throws DBException {
        long id = 0;

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO java2miska.user (user_fullName, user_email, user_password) values(?,?,?)",
                            PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                id = resultSet.getLong(1);
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.createUserReturnId()");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return id;
    }

    public void updateUser(User user) throws DBException {
        if(user == null)return;
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("UPDATE java2miska.user SET user_fullName=?, user_email=?,user_password=? WHERE user_id=?");
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.updateUser");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void deleteUser(User user) throws DBException {
        if(user == null)return;
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("DELETE FROM java2miska.user WHERE user_id=?");
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
            user.setId(0);
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.deleteUser()");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public User getUserById(long id) throws DBException {
        if(id == 0)return null;
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM java2miska.user WHERE user_id=?");
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            User user = new User();
            if(resultSet.next()){
                user.setId(resultSet.getLong("user_id"));
                user.setFullName(resultSet.getString("user_fullName"));
                user.setEmail(resultSet.getString("user_email"));
                user.setPassword(resultSet.getString("user_password"));
            }
            return user;
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.getUserById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<User> getAllUsers() throws DBException {
        List<User> userList = new ArrayList<User>();

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM java2miska.user");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setFullName(resultSet.getString("user_fullName"));
                user.setEmail(resultSet.getString("user_email"));
                user.setPassword(resultSet.getString("user_password"));
                userList.add(user);
            }
            return userList;
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.getAllUsers()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }
}
