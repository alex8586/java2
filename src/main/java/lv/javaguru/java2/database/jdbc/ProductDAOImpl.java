package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl extends DAOImpl<Product> {

    private final String GET_BY_ID = "SELECT * FROM product WHERE ProductID = ?";
    private final String GET_PRODUCT_BY_VENDOR_CODE = "SELECT * FROM product WHERE VendorCode = ?";
    private final String GET_PRODUCT_BY_CATEGORY = "SELECT * FROM product WHERE catID_FK=?";
    private final String GET_ALL = "SELECT * FROM product";
    private final String CREATE_NEW =
            "INSERT INTO product(ProductID, VendorCode, VendorName, VendorDescription, unit, price, DisplayName, DisplayDescription, RemainQTY, catID_FK) VALUES " +
            " (DEFAULT ,?,?,?,?,?,?,?,?,?)";
    private final String GET_NEW_EMPTY_PRODUCT = "INSERT INTO product (ProductID) VALUES (DEFAULT )";
    private final String DELETE_BY_ID = "DELETE FROM product WHERE ProductID=?";
    private final String UPDATE_BY_ID = "UPDATE product SET VendorCode=?, VendorName=?, VendorDescription=?, " +
            "unit=?, price=?, DisplayName=?, DisplayDescription=?, RemainQTY=?, catID_FK=? " +
            " WHERE ProductID=?";

    @Override
    public long create(Product product) {
        return super.create(product, CREATE_NEW);
    }

    @Override
    public void update(Product product) {
        super.update(product, UPDATE_BY_ID);

    }

    @Override
    public void delete(Product product) {
        super.delete(product, DELETE_BY_ID);
    }

    @Override
    public Product getById(long id) {
        return (Product) getById(id, GET_BY_ID);
    }

    public Product getByVendorCode(String vendorCode) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_PRODUCT_BY_VENDOR_CODE);
            preparedStatement.setString(1,vendorCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return buildFromResultSet(resultSet);
            else
                return null;
        } catch (SQLException e) {
            System.out.println("Exception in getProductByID(long id)");
            throw new DBException(e);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Product> getAll() {
        return getAll(GET_ALL);
    }


    public List<Product> getByCategory(Category category) {
        Connection connection = null;
        List<Product> productList = new ArrayList<>();
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_PRODUCT_BY_CATEGORY);
            preparedStatement.setLong(1,category.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productList.add(buildFromResultSet(resultSet));
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting all products by category");
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        return productList;
    }

    public Product getNewEmptyProduct() {
        Connection connection=null;
        Product product = new Product();
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    (GET_NEW_EMPTY_PRODUCT, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                long id = rs.getLong(1);
                product.setId(id);
            }
        } catch (Throwable e) {
            System.out.println("Exception while creating new empty(with ID) record in product");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return product;
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Product product) throws SQLException {
        preparedStatement.setString(1, product.getVendorCode());
        preparedStatement.setString(2, product.getVendorName());
        preparedStatement.setString(3, product.getVendorDescription());
        preparedStatement.setString(4, product.getUnit());
        preparedStatement.setInt(5, product.getPrice());
        preparedStatement.setString(6, product.getDisplayName());
        preparedStatement.setString(7, product.getDisplayDescription());
        preparedStatement.setInt(8, product.getRemainQty());
        preparedStatement.setLong(9, product.getCategoryID());
        if (product.getId() != 0)
            preparedStatement.setLong(10, product.getId());
    }

    @Override
    protected Product buildFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("ProductID"));
        product.setVendorCode(resultSet.getString("VendorCode"));
        product.setCategoryID(resultSet.getLong("catID_FK"));
        product.setDisplayDescription(resultSet.getString("DisplayDescription"));
        product.setDisplayName(resultSet.getString("DisplayName"));
        product.setPrice(resultSet.getInt("price"));
        product.setRemainQty(resultSet.getInt("RemainQTY"));
        product.setVendorName(resultSet.getString("VendorName"));
        product.setVendorDescription(resultSet.getString("VendorDescription"));
        product.setUnit(resultSet.getString("unit"));
        return product;
    }


}
