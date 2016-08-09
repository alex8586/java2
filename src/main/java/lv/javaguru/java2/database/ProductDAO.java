package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;

import java.util.List;

/** ProductDAO interface
 * Created 8/4/2016.
 */
public interface ProductDAO {


    /**
     * returns Product from database with given ID
     *
     * @param id long
     * @return Product
     * @throws DBException
     */
    Product getProductByID(long id) throws DBException;

    /**
     *
     * @param vendorCode String
     * @return Product
     * @throws DBException
     *
     * return Product object from DB  selecting it by VendorCode column
     */
    Product getProductByVendorCode(String vendorCode) throws DBException;

    /**
     *
     * @param category Category
     * @return List<Product>
     * @throws DBException
     *
     * returns List<Product> the list of Product objects selected from DB by its category ID
     */
    List<Product> getProductsByCategory(Category category) throws DBException;

    /**
     *
     * @return List<Product>
     *
     * @throws DBException
     *
     * returns list of all Product objects containing in DB
     */
    List<Product> getAllProducts() throws DBException;

    /**
     *
     * @param newProduct Product
     * @throws DBException

    * method to create new Product in DB with initial properties
     *
     * @see lv.javaguru.java2.domain.builders.ProductBuilder
     */
    long createProduct(Product newProduct) throws DBException;
/**
   *  method to create new empty Product and get it as instance with id from DB
 *  @return Product
 *
 */
        Product getNewEmptyProduct() throws DBException;

    /**
     *
     * @param product Product
     *
     *  method to delete Product with corresponding id  from DB
     */
    void deleteProduct(Product product) throws DBException;

    /**
     *
     * @param productToUpdate Product
     *
     * method to update product record with data from passed Product object
     */

    void updateProduct(Product productToUpdate) throws DBException;





}
