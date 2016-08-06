package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.builders.ProductBuilder;

import java.util.List;

/**
 * Created by Maksims on 8/4/2016.
 */
public interface ProductDAO {

    Product getProductByID(long id) throws DBException;
    Product getProductByVendorCode(String vendorCode);
    List<Product> getProductByCategory(Category category);
    List<Product> getAllProducts();

    /* method to create new Product in DB with initial properties set created in the ProductBuilder */
    void createProduct(ProductBuilder newProduct);

/* method to create new empty Product and get it as instance with id */
        Product getNewEmptyProduct();

    void deleteProduct();

    void updateProduct(Product productToUpdate);





}
