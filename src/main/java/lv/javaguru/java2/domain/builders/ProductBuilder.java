package lv.javaguru.java2.domain.builders;

import lv.javaguru.java2.domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Maksims on 8/7/2016.
 */
public class ProductBuilder {

private List<Product> productList = new LinkedList<>();
    private  Product product;

    private ResultSet resultSet;


    public ProductBuilder() {
        this.product = new Product();

    }

    public ProductBuilder(Product product) {
        this.product = product;
    }

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
        product.setProductID(resultSet.getLong("ProductID"));
        product.setVendorCode(resultSet.getString("VendorCode"));
        product.setCategoryID(resultSet.getLong("catID_FK"));
        product.setDisplayDescription(resultSet.getString("DisplayDescription"));
        product.setDisplayName(resultSet.getString("DisplayName"));
        product.setPrice(resultSet.getInt("price"));
        product.setRemainQty(resultSet.getInt("RemainQTY"));
        product.setVendorName(resultSet.getString("VendorName"));
        product.setVendorDescription(resultSet.getString("VendorDescription"));
        product.setUnit(resultSet.getString("unit"));


        this.product = product;
    }

   public void  setDisplayName(String name){

       product.setDisplayName(name);


    } public void  setVendorCode(String code){

       product.setVendorCode(code);
        product.setCategoryID(1L);


    }
}
