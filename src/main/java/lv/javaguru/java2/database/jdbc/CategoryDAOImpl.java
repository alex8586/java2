package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CategoryDAOImpl extends DAOImpl implements DAO<Category> {

    private final String CREATE_CATEGORY = "INSERT INTO categories(id,name) VALUES(DEFAULT,?)";
    private final String UPDATE_CATEGORY = "UPDATE categories SET name = ? WHERE id = ?";
    private final String DELETE_FROM_CATEGORIES = "DELETE FROM categories WHERE id = ?";
    private final String GET_CATEGORY_BY_ID = "SELECT * FROM categories where id = ?";
    private final String GET_ALL_CATEGORIES = "SELECT * FROM categories";

    public long create(Category category) {
        long newId = 0;
        if(category == null || category.getId() != 0)
            throw new IllegalArgumentException("Exception while execute CategoryDAO.create . Input id != 0 ");

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(CREATE_CATEGORY,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,category.getName());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                newId = rs.getLong(1);
                category.setId(newId);
            }
        }
        catch (Throwable e) {
            System.out.println("Exception while execute CategoryDAO.update");
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
        return newId;
    }

    public void update(Category category) {
        if(category == null || category.getId() == 0)
            throw new IllegalArgumentException("Exception while execute CategoryDAO.create . Input id != 0 ");

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(UPDATE_CATEGORY);
            preparedStatement.setString(1,category.getName());
            preparedStatement.setLong(2,category.getId());
            if(preparedStatement.executeUpdate() != 1){
                throw new IllegalStateException("Exception while execute CategoryDAO.update - 0 or more than 1 record updated");
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
        super.delete(category, DELETE_FROM_CATEGORIES);
    }

    @Override
    protected Category buildFromResultSet(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setName(resultSet.getString("name"));
        category.setId(resultSet.getLong("id"));
        return category;
    }

    public Category getById(long id) {
        return (Category) getById(id, GET_CATEGORY_BY_ID);
    }

    public List<Category> getAll() {
        Connection connection = null;
        List<Category> categories = new LinkedList<Category>();
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CATEGORIES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Category category = new Category();
                category.setName(resultSet.getString("name"));
                category.setId(resultSet.getLong("id"));
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
