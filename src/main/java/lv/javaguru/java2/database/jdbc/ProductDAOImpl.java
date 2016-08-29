package lv.javaguru.java2.database.jdbc;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component("JDBC_ProductDAO")
public class ProductDAOImpl extends DAOImpl<Product> implements ProductDAO {

    private static final String TABLE = "products";

    private static final String GET_BY_ID = "SELECT * FROM " + TABLE + " WHERE id = ?";
    private static final String GET_BY_CATEGORY = "SELECT * FROM " + TABLE + " WHERE category_id = ?";
    private static final String GET_ALL = "SELECT * FROM " + TABLE;
    private static final String CREATE = "INSERT INTO " + TABLE + " (name, description, price, category_id,imgurl,id) VALUES (?, ?, ?, ?, '',DEFAULT)";
    private static final String UPDATE = "UPDATE " + TABLE + " SET name = ?, description = ?, price = ?, category_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM " + TABLE + " WHERE id = ?";

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
        if (product.getId() != 0)
            preparedStatement.setLong(5, product.getId());
    }

    @Override
    protected Product buildFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();

        product.setId(resultSet.getLong("id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setPrice(resultSet.getLong("price"));
        product.setCategoryId(resultSet.getLong("category_id"));

        return product;
    }


}
