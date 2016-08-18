package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends DAOImpl implements DAO<User> {

    private final String CREATE_USER_RETURN_ID = "INSERT INTO users (name, email, password) values(?,?,?)";
    private final String UPDATE_USER = "UPDATE users SET name=?, email=?,password=? WHERE id=?";
    private final String DELETE_USER = "DELETE FROM users WHERE id=?";
    private final String GET_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    private final String GET_ALL_USERS = "SELECT * FROM users";
    private final String GET_BY_EMAIL = "SELECT * FROM users WHERE email=?";

    public long create(User user) {
        if (user == null || user.getId() != 0)
            throw new IllegalArgumentException("Exception while execute UserDAOImpl.create() . Input id != 0 ");
        Connection connection = null;
        long newId = 0;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    (CREATE_USER_RETURN_ID, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                newId = resultSet.getLong(1);
                user.setId(newId);
            }
        } catch (SQLException e) {
            System.out.println("Exception while execute UserDAOImpl.create()" + e);
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return newId;
    }

    public void update(User user) {
        if (user == null || user.getId() == 0)
            throw new IllegalArgumentException("Exception while execute CategoryDAOImpl.create . Input id != 0 ");

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setLong(4, user.getId());
            if (preparedStatement.executeUpdate() != 1) {
                throw new IllegalStateException("Exception while execute UserDAOImpl.update - 0 or more than 1 record updated");
            }
        } catch (SQLException e) {
            System.out.println("Exception while execute UserDAOImpl.update");
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public void delete(User user) {
        super.delete(user, DELETE_USER);
    }

    @Override
    protected User buildFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setFullName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }

    private User getFromStatement(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return buildFromResultSet(resultSet);
        else
            return null;
    }

    public User getById(long id) {
        return (User) super.getById(id, GET_USER_BY_ID);
    }

    public List<User> getAll() {
        List<User> userList = new ArrayList<User>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = buildFromResultSet(resultSet);
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

    public User getByEmail(String email) {
        if (email == null || email.equals("")) {
            throw new IllegalArgumentException("Exception while execute UserDAOImpl.getByEmail()");
        }
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMAIL);
            preparedStatement.setString(1, email);
            return getFromStatement(preparedStatement);
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.getByEmail()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }
}
