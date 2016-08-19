package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl extends DAOImpl<User> {

    private final String CREATE_NEW = "INSERT INTO users (name, email, password,id) values(?,?,?,DEFAULT)";
    private final String UPDATE_BY_ID = "UPDATE users SET name=?, email=?,password=? WHERE id=?";
    private final String DELETE_BY_ID = "DELETE FROM users WHERE id=?";
    private final String GET_BY_ID = "SELECT * FROM users WHERE id=?";
    private final String GET_ALL = "SELECT * FROM users";
    private final String GET_BY_EMAIL = "SELECT * FROM users WHERE email=?";

    @Override
    public long create(User user) {
        return super.create(user, CREATE_NEW);
    }

    @Override
    public void update(User user) {
        update(user, UPDATE_BY_ID);
    }

    @Override
    public void delete(User user) {
        super.delete(user, DELETE_BY_ID);
    }

    public User getById(long id) {
        return (User) super.getById(id, GET_BY_ID);
    }

    public List<User> getAll() {
        return getAll(GET_ALL);
    }

    private User getFromStatement(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return buildFromResultSet(resultSet);
        else
            return null;
    }

    public User getByEmail(String email) {
        return (User) getByCondition(GET_BY_EMAIL, email);
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getFullName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPassword());
        if (user.getId() != 0)
            preparedStatement.setLong(4, user.getId());
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

}
