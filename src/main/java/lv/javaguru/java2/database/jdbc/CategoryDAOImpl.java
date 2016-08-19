package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class CategoryDAOImpl extends DAOImpl implements DAO<Category> {

    private static final String TABLE = "categories";

    private static final String CREATE = "INSERT INTO " + TABLE + "(id, name) VALUES(DEFAULT, ?);";
    private static final String UPDATE = "UPDATE " + TABLE + " SET name = ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM " + TABLE + " WHERE id = ?;";
    private static final String GET_BY_ID = "SELECT * FROM " + TABLE + " WHERE id = ?;";
    private static final String GET_ALL = "SELECT * FROM " + TABLE + ";";

    public long create(Category category) {
        long newId = 0;
        if (category == null || category.getId() != 0) {
            throw new IllegalArgumentException("Exception while executing CategoryDAO.create: id cannot be 0.");
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                newId = resultSet.getLong(1);
                category.setId(newId);
            }
        }
        catch (Throwable e) {
            System.out.println("Exception while executing CategoryDAO.update");
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return newId;
    }

    public void update(Category category) {
        if(category == null || category.getId() == 0)
            throw new IllegalArgumentException("Exception while executing CategoryDAO.create: id cannot be 0.");

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setLong(2, category.getId());
            if(preparedStatement.executeUpdate() != 1){
                throw new IllegalStateException("Exception while executing CategoryDAO.update - 0 or more than 1 record updated.");
            }
        }
        catch (Throwable e) {
            System.out.println("Exception while execute CategoryDAO.update");
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    public void delete(Category category) {
        if(category == null || category.getId() == 0)
            return;
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setLong(1, category.getId());
            preparedStatement.executeUpdate();
            category.setId(0);
        }
        catch (Throwable e) {
            System.out.println("Exception while execute CategoryDAO.delete");
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    public Category getById(long id) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            Category category = null;
            if(resultSet.next()) {
                category = new Category(resultSet.getLong("id"), resultSet.getString("name"));
            }
            return category;
        }
        catch (Throwable e) {
            System.out.println("Exception while execute CategoryDAO.getByid");
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    public List<Category> getAll() {
        Connection connection = null;
        List<Category> categories = new LinkedList<Category>();
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Category category = new Category(resultSet.getLong("id"), resultSet.getString("name"));
                categories.add(category);
            }
            return categories;
        } catch (Throwable e) {
            System.out.println("Exception while execute CategoryDAO.getAll");
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }
}
