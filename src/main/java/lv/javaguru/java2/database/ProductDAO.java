package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Product;

/**
 * Created by algis on 16.25.8.
 */
public interface ProductDAO extends CrudDAO<Product> {
    Product getByVendorCode(String vendorCode);
}
