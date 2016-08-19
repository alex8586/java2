package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDAOImpl extends DAOImpl<Product> {

    private final String GET_BY_ID = "SELECT * FROM product WHERE ProductID = ?";
    private final String GET_BY_VENDOR_CODE = "SELECT * FROM product WHERE VendorCode = ?";
    private final String GET_BY_CATEGORY = "SELECT * FROM product WHERE catID_FK=?";
    private final String GET_ALL = "SELECT * FROM product";
    private final String GET_ALL_AVAILABLE = "SELECT * FROM product WHERE RemainQTY > 0";
    private final String CREATE_NEW =
            "INSERT INTO product(ProductID, VendorCode, VendorName, VendorDescription, unit, price, DisplayName, DisplayDescription, RemainQTY, catID_FK) VALUES " +
            " (DEFAULT ,?,?,?,?,?,?,?,?,?)";
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
        return (Product) getByCondition(GET_BY_VENDOR_CODE, vendorCode);
    }

    @Override
    public List<Product> getAll() {
        return getAll(GET_ALL);
    }

    public List<Product> getAllAvailable() {
        return getAll(GET_ALL_AVAILABLE);
    }

    public List<Product> getAllByCategory(Category category) {
        return getAllByFK(GET_BY_CATEGORY, category);
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
