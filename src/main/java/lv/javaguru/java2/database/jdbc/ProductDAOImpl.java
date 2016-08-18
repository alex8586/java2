package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DAO;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.builders.ProductBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDAOImpl extends DAOImpl implements DAO<Product> {

    private final String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE ProductID = ?";
    private final String GET_PRODUCT_BY_VENDOR_CODE = "SELECT * FROM product WHERE VendorCode = ?";
    private final String GET_PRODUCT_BY_CATEGORY = "SELECT * FROM product WHERE catID_FK=?";
    private final String GET_ALL_PRODUCTS = "SELECT * FROM product";
    private final String CREATE_PRODUCT =
            "INSERT INTO product(ProductID, VendorCode, VendorName, VendorDescription, unit, price, DisplayName, DisplayDescription, RemainQTY, catID_FK) VALUES " +
            " (DEFAULT ,?,?,?,?,?,?,?,?,?)";
    private final String GET_NEW_EMPTY_PRODUCT = "INSERT INTO product (ProductID) VALUES (DEFAULT )";
    private final String DELETE_PRODUCT = "DELETE FROM product WHERE ProductID=?";
    private final String UPDATE_PRODUCT = "UPDATE product SET VendorCode=?, VendorName=?, VendorDescription=?, " +
            "unit=?, price=?, DisplayName=?, DisplayDescription=?, RemainQTY=?, catID_FK=? " +
            " WHERE ProductID=?";

    @Override
    public long create(Product product) {
        long newId = 0;
        if(product == null || product.getId() != 0)
            throw new IllegalArgumentException("Exception while execute ProductDAOImpl.create . Input id != 0 ");

        Connection connection = null;
        try {
            connection=getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    (CREATE_PRODUCT,PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,product.getVendorCode());
            preparedStatement.setString(2,product.getVendorName());
            preparedStatement.setString(3,product.getVendorDescription());
            preparedStatement.setString(4,product.getUnit());
            preparedStatement.setInt(5,product.getPrice());
            preparedStatement.setString(6,product.getDisplayName());
            preparedStatement.setString(7,product.getDisplayDescription());
            preparedStatement.setInt(8,product.getRemainQty());
            preparedStatement.setLong(9,product.getCategoryID());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                newId = rs.getLong(1);
                product.setId(newId);
            }
        }
        catch (Throwable e) {
            System.out.println("Exception while execute ProductDAOImpl.create()");
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        return newId;
    }

    @Override
    public void update(Product product) {
        if(product == null || product.getId() == 0)
            throw new IllegalArgumentException("Exception while execute ProductDAOImpl.update . Input id != 0 ");

        Connection connection=null;
        try {
            connection=getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setString(1,product.getVendorCode());
            preparedStatement.setString(2,product.getVendorName());
            preparedStatement.setString(3,product.getVendorDescription());
            preparedStatement.setString(4,product.getUnit());
            preparedStatement.setInt(5,product.getPrice());
            preparedStatement.setString(6,product.getDisplayName());
            preparedStatement.setString(7,product.getDisplayDescription());
            preparedStatement.setInt(8,product.getRemainQty());
            preparedStatement.setLong(9,product.getCategoryID());
            preparedStatement.setLong(10,product.getId());
            preparedStatement.executeUpdate();
            if(preparedStatement.executeUpdate() != 1){
                throw new IllegalStateException("Exception while execute ProductDAOImpl.update - 0 or more than 1 record updated");
            }
        } catch (Throwable e) {
            System.out.println("Exception while updating product");
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(Product product) {
        super.delete(product, DELETE_PRODUCT);
    }


    @Override
    public Product getById(long id) {

        Connection connection = null;
        Product product = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_PRODUCT_BY_ID);
            preparedStatement.setLong(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            ProductBuilder productBuilder = new ProductBuilder(resultSet);
            product = productBuilder.getProduct();

        } catch (SQLException e) {
            System.out.println("Exception in getProductByID(long id)");
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
        return product;
    }

    public Product getByVendorCode(String vendorCode) {
        Connection connection = null;
        Product product = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_PRODUCT_BY_VENDOR_CODE);
            preparedStatement.setString(1,vendorCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            ProductBuilder productBuilder = new ProductBuilder(resultSet);
            if(resultSet.first()) {
                product = productBuilder.getProduct();
            } else {
                System.out.println("not found");}

            if(resultSet.next()) System.out.println("more than one with this VendorCode");

        } catch (SQLException e) {
            System.out.println("Exception in getProductByID(long id)");
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        Connection connection = null;
        List<Product> productList = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_ALL_PRODUCTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            ProductBuilder productBuilder = new ProductBuilder(resultSet);
            productList = productBuilder.getProductList();
        } catch (Throwable e) {
            System.out.println("Exception while getting all products list");
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        return productList;
    }

    public List<Product> getByCategory(Category category) {
        Connection connection = null;
        List<Product> productList = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_PRODUCT_BY_CATEGORY);
            preparedStatement.setLong(1,category.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            ProductBuilder productBuilder = new ProductBuilder(resultSet);
            productList = productBuilder.getProductList();
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

}
