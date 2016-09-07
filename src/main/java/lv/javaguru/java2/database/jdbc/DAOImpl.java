package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.BaseEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAOImpl<T extends BaseEntity> extends JdbcConnector {

    public abstract long create(T t);
    public abstract void update(T t);
    public abstract void delete(T t);
    public abstract T getById(long id);
    public abstract List<T> getAll();

    protected abstract void fillPreparedStatement(PreparedStatement preparedStatement, T baseEntity) throws SQLException;

    protected long create(T baseEntity, String sql) {
        long newId = 0;
        if (baseEntity == null || baseEntity.getId() != 0)
            throw new IllegalArgumentException("Exception while execute create for " + sql + ". null or existing object received");
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            fillPreparedStatement(preparedStatement, baseEntity);
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                newId = rs.getLong(1);
                baseEntity.setId(newId);
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute create for " + sql);
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return newId;
    }

    protected void update(T baseEntity, String sql) {
        if (baseEntity == null || baseEntity.getId() == 0)
            throw new IllegalArgumentException("Exception while execute update with " + sql + " . null or new object received");

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            fillPreparedStatement(preparedStatement, baseEntity);
            if (preparedStatement.executeUpdate() != 1) {
                throw new IllegalStateException("Exception while execute update for " + baseEntity.getClass().getSimpleName() + " - 0 or more than 1 record updated");
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute update for " + baseEntity.getClass().getSimpleName());
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    protected void delete(T baseEntity, String sql) {
        if (baseEntity == null || baseEntity.getId() == 0)
            return;
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            preparedStatement.setLong(1, baseEntity.getId());
            preparedStatement.executeUpdate();
            baseEntity.setId(0);
        } catch (Throwable e) {
            System.out.println("Exception while deleting " + baseEntity.getClass().getSimpleName());
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    protected abstract T buildFromResultSet(ResultSet resultSet) throws SQLException;

    protected BaseEntity getById(long id, final String sql) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return buildFromResultSet(resultSet);
            else
                return null;
        } catch (Throwable e) {
            System.out.println("Exception while execute getById with " + sql);
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    protected BaseEntity getByCondition(final String sql, Object value) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return buildFromResultSet(resultSet);
            else
                return null;
        } catch (Throwable e) {
            System.out.println("Exception while execute getByCondition with " + sql);
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    protected List<T> getAll(String sql) {
        List<T> entityList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                entityList.add(buildFromResultSet(resultSet));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute get all with " + sql);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return entityList;
    }

    protected List<T> getAllByFK(String sql, BaseEntity entity) {
        List<T> entityList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            preparedStatement.setLong(1, entity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                entityList.add(buildFromResultSet(resultSet));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute get all with " + sql);
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return entityList;
    }
}