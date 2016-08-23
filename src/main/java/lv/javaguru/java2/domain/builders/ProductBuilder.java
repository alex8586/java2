package lv.javaguru.java2.domain.builders;

import lv.javaguru.java2.domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductBuilder {

    private List<Product> productList = new LinkedList<>();
    private Product product;
    private ResultSet resultSet;

    public ProductBuilder(ResultSet resultSet) {
        this.resultSet = resultSet;
        try {
            while(resultSet.next()){
                setProduct(resultSet);
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Exception while build products from resultset");
            e.printStackTrace();
        }
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(ResultSet resultSet) throws SQLException {
        product=new Product();
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
        product.setImgUrl(resultSet.getString("imgurl"));
    }

}
