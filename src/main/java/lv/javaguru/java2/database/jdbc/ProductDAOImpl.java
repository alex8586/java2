package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.builders.ProductBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Maksims on 8/4/2016.
 */
public class ProductDAOImpl extends MiskaConnection implements ProductDAO {

    public ProductDAOImpl(){

        initDatabaseConnectionProperties();
        registerJDBCDriver();
    }


    @Override
    public Product getProductByID(long id) throws DBException {

       Connection con = getConnection();
ProductBuilder builder = ProductBuilder.productBuilder;

        builder.createAbstractProduct();
        builder.addVendorCode();

//        con.prepareStatement("")
        return null;
    }

    @Override
    public Product getProductByVendorCode(String vendorCode) {
        return null;
    }

    @Override
    public List<Product> getProductByCategory(Category category) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public void createProduct(ProductBuilder newProduct) {

    }

    @Override
    public Product getNewEmptyProduct() {
Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO  java2miska.product (ProductID) VALUES (DEFAULT )",PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            long id = preparedStatement.getGeneratedKeys().getLong(1);
            Product product = new Product();

            product.setId(id);


            return product;



        } catch (DBException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public void deleteProduct() {

    }

    @Override
    public void updateProduct(Product productToUpdate) {

    }
}
