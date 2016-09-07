package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.helpers.CategoryTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Component("JDBC_ProductDAO")
public class ProductDAOImpl extends DAOImpl<Product> implements ProductDAO {

    private static final String TABLE = "products";
    private static final String GET_BY_ID = "SELECT * FROM " + TABLE + " WHERE id = ?";
    private static final String GET_BY_CATEGORY = "SELECT * FROM " + TABLE + " WHERE category_id = ?";
    private static final String GET_ALL = "SELECT * FROM " + TABLE;
    private static final String CREATE = "INSERT INTO " + TABLE + " (name, description, price, category_id,imgurl,id) VALUES (?, ?, ?, ?, ?, DEFAULT)";
    private static final String UPDATE = "UPDATE " + TABLE + " SET name = ?, description = ?, price = ?, category_id = ?,imgurl = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM " + TABLE + " WHERE id = ?";
    private static final String GET_RANDOM_PRODUCT = "SELECT * FROM "+ TABLE + " ORDER BY rand() LIMIT ?";
    private static final String GET_RANDOM_PRODUCT_WITHOUT_CURRENT_CATEGORY_ID = "SELECT * FROM "+ TABLE + " WHERE category_id NOT LIKE ? ORDER BY rand() LIMIT 1";
    private static final String GET_BY_CATEGORY_TREE = "SELECT * FROM " + TABLE + " WHERE category_id IN ";
    @Autowired
    CategoryTree categoryTree;

    @Override
    public long create(Product product) {
        return super.create(product, CREATE);
    }

    @Override
    public void update(Product product) {
        super.update(product, UPDATE);
    }

    @Override
    public void delete(Product product) {
        super.delete(product, DELETE);
    }

    @Override
    public Product getById(long id) {
        return (Product) super.getById(id, GET_BY_ID);
    }

    @Override
    public List<Product> getAll() {
        return super.getAll(GET_ALL);
    }

    public List<Product> getAllByCategory(Category category) {
        return super.getAllByFK(GET_BY_CATEGORY, category);
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Product product) throws SQLException {
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, product.getDescription());
        preparedStatement.setLong(3, product.getPrice());
        preparedStatement.setLong(4, product.getCategoryId());
        preparedStatement.setString(5, product.getImgUrl());
        if (product.getId() != 0)
            preparedStatement.setLong(6, product.getId());
    }

    @Override
    protected Product buildFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();

        product.setId(resultSet.getLong("id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setPrice(resultSet.getLong("price"));
        product.setCategoryId(resultSet.getLong("category_id"));
        product.setImgUrl(resultSet.getString("imgurl"));

        return product;
    }

    /*
    @Override
    public Product getRandomProduct(){
        return (Product) super.getByCondition(GET_RANDOM_PRODUCT, 1);
    }

    @Override
    public Product getRandomProductWithoutCurrentCategoryId(long id) {
        return (Product) super.getByCondition(GET_RANDOM_PRODUCT_WITHOUT_CURRENT_CATEGORY_ID, id);
    }
    */


    @Override
    public Product getRandomProductWithoutCurrentCategoryId(long id){
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    (GET_RANDOM_PRODUCT_WITHOUT_CURRENT_CATEGORY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return buildFromResultSet(resultSet);
            else
                return null;
        } catch (Throwable e) {
            System.out.println("Exception while execute getRandomProduct");
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Product getRandomProduct(){
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_RANDOM_PRODUCT);
            preparedStatement.setLong(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return buildFromResultSet(resultSet);
            else
                return null;
        } catch (Throwable e) {
            System.out.println("Exception while execute getRandomProduct");
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List getByCategoryTree(Category category) {
        List<Category> categories = categoryTree.getAncestors(category);
        List<Long> ids = categories.stream().map(cat -> cat.getId()).collect(Collectors.toList());
        ids.add(category.getId());
        String inIds = ids.toString();
        String sql = GET_BY_CATEGORY_TREE + "(-1," + inIds.substring(1, inIds.length() - 1) + ")";
        return super.getAll(sql);
    }

}
