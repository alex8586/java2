package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.CountVisitorsDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.CountVisitor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component("JDBC_CountVisitorsDAO")
public class CountVisitorsDAOImpl extends JdbcConnector implements CountVisitorsDAO {

    private final static String CREATE_COUNT_VISITOR = "INSERT INTO visitors_counter (ip, product_id, counter) VALUES (?,?,?)";
    private final static String UPDATE_COUNT_VISITOR = "UPDATE visitors_counter SET product_id=?, ip=?, counter=? WHERE id=?";
    private final static String GET_BY_PRODUCT = "SELECT * FROM visitors_counter WHERE product_id=?";
    private final static String GET_BY_IP = "SELECT * FROM visitors_counter WHERE ip=?";
    private final static String GET_BY_PRODUCT_AND_USER = "SELECT * FROM visitors_counter WHERE product_id=? and user_id=?";
    private final static String GET_ALL_COUNT = "SELECT * FROM visitors_counter";

    @Override
    public void createCountVisitor(CountVisitor countVisitor) {
        if (countVisitor == null || countVisitor.getId() != 0)
            throw new IllegalArgumentException("Exception while execute createCountVisitor(). null or existing object received");
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    (CREATE_COUNT_VISITOR);
            preparedStatement.setString(1, countVisitor.getIp());
            preparedStatement.setLong(2, countVisitor.getProductId());
            preparedStatement.setInt(3, countVisitor.getCounter());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute create()" + CREATE_COUNT_VISITOR);
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void updateCountVisitor(CountVisitor countVisitor) {
        if (countVisitor == null || countVisitor.getId() == 0)
            throw new IllegalArgumentException("Exception while execute update(). null or new object received");

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    (UPDATE_COUNT_VISITOR);
            preparedStatement.setLong(1, countVisitor.getProductId());
            preparedStatement.setString(2, countVisitor.getIp());
            preparedStatement.setInt(3, countVisitor.getCounter());
            preparedStatement.setLong(4, countVisitor.getId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute update()" + UPDATE_COUNT_VISITOR);
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
    public int getCountByIp(String ip) {
        Connection connection = null;
        int counter = 0;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_IP);
            preparedStatement.setString(1, ip);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                counter = resultSet.getInt(1);
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute getCountByIp" + GET_BY_IP);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return counter;
    }

    @Override
    public int getCountByProductAndVisitor(long productId, long ip) {
        Connection connection = null;
        int counter = 0;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    (GET_BY_PRODUCT_AND_USER);
            preparedStatement.setLong(1, productId);
            preparedStatement.setLong(2, ip);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                counter = resultSet.getInt(1);
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute getCountByProductAndVisitor() " + GET_BY_PRODUCT_AND_USER);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return counter;
    }

    @Override
    public List getAllCount() {
        List<CountVisitor> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    (GET_ALL_COUNT);
            ResultSet resultSet = preparedStatement.executeQuery();
            CountVisitor countVisitor;
            while (resultSet.next()) {
                countVisitor = new CountVisitor();
                countVisitor.setId(resultSet.getLong(1));
                countVisitor.setIp(resultSet.getString(2));
                countVisitor.setProductId(resultSet.getLong(3));
                countVisitor.setCounter(resultSet.getInt(4));
                list.add(countVisitor);
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute getAllCount " + GET_ALL_COUNT);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return list;
    }
}
