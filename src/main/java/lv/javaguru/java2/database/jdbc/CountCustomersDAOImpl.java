package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.CountCustomersDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.CountCustomer;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component("JDBC_CountCustomersDAO")
public class CountCustomersDAOImpl extends JdbcConnector implements CountCustomersDAO{

    private final static String CREATE_COUNT_CUSTOMER = "INSERT INTO customers_counter (user_id, product_id, counter) VALUES (?,?,?)";
    private final static String UPDATE_COUNT_CUSTOMER = "UPDATE customers_counter SET user_id=?, product_id=?, counter=? WHERE id=?";
    private final static String GET_BY_PRODUCT = "SELECT * FROM customers_counter WHERE product_id=?";
    private final static String GET_BY_CUSTOMER = "SELECT * FROM customers_counter WHERE user_id=?";
    private final static String GET_BY_PRODUCT_AND_USER = "SELECT * FROM customers_counter WHERE product_id=? and user_id=?";
    private final static String GET_ALL_COUNT = "SELECT * FROM customers_counter";

    @Override
    public void createCountCustomer(CountCustomer countCustomer) {
        if (countCustomer == null || countCustomer.getId() != 0)
            throw new IllegalArgumentException("Exception while execute createCountCustomer(). null or existing object received");
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    (CREATE_COUNT_CUSTOMER);
            preparedStatement.setLong(1, countCustomer.getUserId());
            preparedStatement.setLong(2, countCustomer.getProductId());
            preparedStatement.setInt(3, countCustomer.getCounter());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute createCountCustomer()" + CREATE_COUNT_CUSTOMER);
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void updateCountCustomer(CountCustomer countCustomer) {
        if (countCustomer == null || countCustomer.getId() == 0)
            throw new IllegalArgumentException("Exception while execute updateCountCustomer(). null or new object received");

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    (UPDATE_COUNT_CUSTOMER);
            preparedStatement.setLong(1, countCustomer.getUserId());
            preparedStatement.setLong(2, countCustomer.getProductId());
            preparedStatement.setInt(3, countCustomer.getCounter());
            preparedStatement.setLong(4, countCustomer.getId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute updateCountCustomer()" + UPDATE_COUNT_CUSTOMER);
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public int getCountByProduct(long productId) {
        Connection connection = null;
        int counter = 0;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_PRODUCT);
            preparedStatement.setLong(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                counter = resultSet.getInt(1);
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute getCountByProduct()" + GET_BY_PRODUCT);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return counter;
    }

    @Override
    public int getCountByCustomer(long userId) {
        Connection connection = null;
        int counter = 0;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_CUSTOMER);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                counter = resultSet.getInt(1);
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute getCountByCustomer()" + GET_BY_CUSTOMER);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return counter;
    }

    public int getCountByProductAndVisitor(long productId, long userId) {
        Connection connection = null;
        int counter = 0;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    (GET_BY_PRODUCT_AND_USER);
            preparedStatement.setLong(1, productId);
            preparedStatement.setLong(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                counter = resultSet.getInt(1);
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute getCountByProductAndVisitor()");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return counter;
    }

    @Override
    public List getAllCount() {
        List<CountCustomer> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    (GET_ALL_COUNT);
            ResultSet resultSet = preparedStatement.executeQuery();
            CountCustomer countCustomer;
            while (resultSet.next()) {
                countCustomer = new CountCustomer();
                countCustomer.setId(resultSet.getLong(1));
                countCustomer.setUserId(resultSet.getLong(2));
                countCustomer.setProductId(resultSet.getLong(3));
                countCustomer.setCounter(resultSet.getInt(4));
                list.add(countCustomer);
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
