package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.DAOImpl;
import lv.javaguru.java2.domain.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class CategoryDAO extends DAOImpl {

    public long create(Category category) throws DBException {
        if(category.getId() != 0)
            return category.getId();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO categories(id,name) VALUES(DEFAULT,?)" ,
                            PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,category.getName());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            long id = 0;
            if (rs.next()){
                id = rs.getLong(1);
                category.setId(id);
            }
            return id;
        }
        catch (Throwable e) {
            System.out.println("Exception while execute CategoryDAO.create");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    public void update(Category category) throws DBException{
        if(category.getId() == 0)
            return ;

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE categories SET name = ? WHERE id = ?");
            preparedStatement.setString(1,category.getName());
            preparedStatement.setLong(2,category.getId());
            preparedStatement.executeUpdate();
        }
        catch (Throwable e) {
            System.out.println("Exception while execute CategoryDAO.update");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    public void delete(Category category) throws DBException{
        if(category.getId() == 0)
            return ;
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM categories WHERE id = ?");
            preparedStatement.setLong(1,category.getId());
            preparedStatement.executeUpdate();
            category.setId(0);
        }
        catch (Throwable e) {
            System.out.println("Exception while execute CategoryDAO.delete");
            e.printStackTrace();
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }

    }

    public Category getById(long id) throws DBException{
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM categories where id = ?");
            preparedStatement.setLong(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            Category category = null;
            if(resultSet.next()) {
                category = new Category();
                category.setName(resultSet.getString("name"));
                category.setId(resultSet.getLong("id"));
            }
            return category;
        } catch (Throwable e) {
            System.out.println("Exception while execute CategoryDAO.getByid");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<Category> getAll() throws DBException{
        Connection connection = null;
        List<Category> categories = new LinkedList<Category>();

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM categories");
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
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }
}
