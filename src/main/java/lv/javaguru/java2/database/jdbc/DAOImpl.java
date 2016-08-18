package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.BaseEntity;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DAOImpl {

    private static final String DB_CONFIG_FILE = "database.properties";

    private String dbBaseUrl  = null;
    private String dbSchema = null;
    private String userName  = null;
    private String password = null;

    public DAOImpl() {
        registerJDBCDriver();
        initDatabaseConnectionProperties();
    }

    private void registerJDBCDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Exception while registering JDBC driver!");
            e.printStackTrace();
        }
    }

    private void initDatabaseConnectionProperties() {
        Properties properties = new Properties();
        try {
            properties.load(DAOImpl.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILE));
            dbBaseUrl = properties.getProperty("dbBaseUrl");
            dbSchema = properties.getProperty("dbSchema");
            userName = properties.getProperty("userName");
            password = properties.getProperty("password");
        } catch (IOException e){
            System.out.println("Exception while reading JDBC configuration from file = " + DB_CONFIG_FILE);
            e.printStackTrace();
        }
    }

    protected Connection getConnection() {
        try{
            return DriverManager.getConnection(dbBaseUrl + dbSchema, userName, password);
        } catch (SQLException e) {
            System.out.println("Exception while getting connection to database");
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    protected void closeConnection(Connection connection) {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Exception while closing connection to database");
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    protected void delete(BaseEntity baseEntity, String DELETE_STATEMENT) {
        if (baseEntity == null || baseEntity.getId() == 0)
            return;
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(DELETE_STATEMENT);
            preparedStatement.setLong(1, baseEntity.getId());
            preparedStatement.executeUpdate();
            baseEntity.setId(0);
        } catch (Throwable e) {
            System.out.println("Exception while deleting " + baseEntity.getClass().getSimpleName());
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }
}