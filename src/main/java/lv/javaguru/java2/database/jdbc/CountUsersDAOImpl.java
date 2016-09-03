package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.CountUsersDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.CountUser;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component("JDBC_CountUsersDAO")
public class CountUsersDAOImpl extends JdbcConnector implements CountUsersDAO {

    private final static String CREATE = "INSERT INTO users_counter (user_id, product_id, counter) VALUES (?,?,?)";
    private final static String UPDATE = "UPDATE users_counter SET user_id=?, product_id=?, counter=? WHERE id=?";
    private final static String DELETE = "DELETE FROM users_counter WHERE id=?";
    private final static String GET_BY_ID = "SELECT * FROM users_counter WHERE id =?";
    private final static String GET_BY_PRODUCT_ID = "SELECT sum(counter) FROM users_counter WHERE product_id=?";
    private final static String GET_BY_USER_ID = "SELECT sum(counter) FROM users_counter WHERE user_id=?";
    private final static String GET_SUM_COUNT = "SELECT sum(counter) FROM users_counter";
    private final static String GET_ALL_COUNT = "SELECT * FROM users_counter";

    @Override
    public long create(CountUser countUser) {
        if (countUser == null || countUser.getId() != 0)
            throw new IllegalArgumentException("Exception while execute create(). null or existing object received");
        Connection connection = null;
        long countCustomerId = 0;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    (CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, countUser.getUserId());
            preparedStatement.setLong(2, countUser.getProductId());
            preparedStatement.setInt(3, countUser.getCounter());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next())
                countCustomerId = resultSet.getLong(1);
        } catch (Throwable e) {
            System.out.println("Exception while execute create()" + CREATE);
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return countCustomerId;
    }

    @Override
    public void update(CountUser countUser) {
        if (countUser == null || countUser.getId() == 0)
            throw new IllegalArgumentException("Exception while execute update(). null or new object received");

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    (UPDATE);
            preparedStatement.setLong(1, countUser.getUserId());
            preparedStatement.setLong(2, countUser.getProductId());
            preparedStatement.setInt(3, countUser.getCounter());
            preparedStatement.setLong(4, countUser.getId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute update()" + UPDATE);
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(CountUser countUser) {
        if (countUser == null || countUser.getId() == 0)
            return;
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setLong(1, countUser.getId());
            preparedStatement.executeUpdate();
            countUser.setId(0);
        } catch (Throwable e) {
            System.out.println("Exception while execute delete()" + DELETE);
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public CountUser getById(long id) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    (GET_BY_ID);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            CountUser countUser;
            if (resultSet.next()){
                countUser = new CountUser();
                countUser.setId(resultSet.getLong("id"));
                countUser.setUserId(resultSet.getLong("user_id"));
                countUser.setProductId(resultSet.getLong("product_id"));
                countUser.setCounter(resultSet.getInt("counter"));
                return countUser;
            }
            else
                return null;
        } catch (Throwable e) {
            System.out.println("Exception while execute getById " + GET_BY_ID);
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public int getCountByProductId(long productId) {
        Connection connection = null;
        int counter = 0;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_PRODUCT_ID);
            preparedStatement.setLong(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                counter = resultSet.getInt(1);
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute getCountByProductId()" + GET_BY_PRODUCT_ID);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return counter;
    }

    @Override
    public int getCountByUserId(long userId) {
        Connection connection = null;
        int counter = 0;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USER_ID);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                counter = resultSet.getInt(1);
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute getCountByUserId()" + GET_BY_USER_ID);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return counter;
    }

    public int getSumCountFromAllTable() {
        Connection connection = null;
        int counter = 0;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    (GET_SUM_COUNT);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                counter = resultSet.getInt(1);
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute getSumCountFromAllTable()");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return counter;
    }

    @Override
    public List getAllCount() {
        List<CountUser> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    (GET_ALL_COUNT);
            ResultSet resultSet = preparedStatement.executeQuery();
            CountUser countUser;
            while (resultSet.next()) {
                countUser = new CountUser();
                countUser.setId(resultSet.getLong(1));
                countUser.setUserId(resultSet.getLong(2));
                countUser.setProductId(resultSet.getLong(3));
                countUser.setCounter(resultSet.getInt(4));
                list.add(countUser);
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute getAllCount" + GET_ALL_COUNT);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return list;
    }
}
