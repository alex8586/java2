package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Maksims on 8/4/2016.
 */
public class MiskaConnection implements MiskaConnectionInterface {
    private String dbBaseUrl;
    private String dbSchema;
    private String userName;
    private String password;

    public MiskaConnection(){


    }

    @Override
    public void registerJDBCDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found");
            e.printStackTrace();
        }
    }

    @Override
    public void initDatabaseConnectionProperties() {

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
    @Override
    public void setDbSchema(String dbSchema){
        this.dbSchema = dbSchema;
    }
    @Override
    public Connection getConnection() throws DBException {
        try{
            return DriverManager.getConnection(dbBaseUrl , userName, password);
        } catch (SQLException e) {
            System.out.println("Exception while getting connection to database");
            e.printStackTrace();
            throw new DBException(e);
        }
    }
    @Override
    public void closeConnection(Connection connection) throws DBException {
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

    @Override
    public String toString() {
        return "MiskaConnection{" +
                "dbBaseUrl='" + dbBaseUrl + '\'' +
                ", dbSchema='" + dbSchema + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

