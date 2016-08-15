package lv.javaguru.java2;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.DAOImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseCleaner extends DAOImpl {

    private List<String> getTableNames(Connection connection) {
        List<String> tableList = new ArrayList<String>();
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rs = metaData.getTables(null, null, "%", types);
            while (rs.next())
                tableList.add(rs.getString("TABLE_NAME"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableList;
    }

    public void cleanDatabase() throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            setupConnection(connection);
            setForeignKeyChecks(connection, false);
            deleteTables(connection);
            setForeignKeyChecks(connection, true);
        } catch (Throwable e) {
            System.out.println("Exception while execute cleanDatabase()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    private void setupConnection(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        connection.prepareStatement("SET SQL_SAFE_UPDATES=0;").execute();
    }

    private void setForeignKeyChecks(Connection connection, boolean foreignKeyChecks) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("SET FOREIGN_KEY_CHECKS=" + foreignKeyChecks);
        connection.commit();
    }

    private void deleteTables(Connection connection) throws SQLException {
        for (String table : getTableNames(connection)) {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from " + table);
            preparedStatement.executeUpdate();
        }
        connection.commit();
    }

}