package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;

import java.sql.Connection;

/**
 * Created by Maksims on 8/4/2016.
 */
public interface MiskaConnectionInterface {
    static final String DB_CONFIG_FILE = "database.properties";


    void registerJDBCDriver();
    void initDatabaseConnectionProperties();
    void setDbSchema(String dbSchema);
    Connection getConnection() throws DBException;
    void closeConnection(Connection connection) throws DBException;

}
