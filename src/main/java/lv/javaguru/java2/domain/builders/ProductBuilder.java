package lv.javaguru.java2.domain.builders;

import lv.javaguru.java2.domain.Product;

/**
 * Created by Maksims on 8/4/2016.
 */
public class ProductBuilder implements ProductBuilderInterface {

    public static ProductBuilder productBuilder = new ProductBuilder();
 private Product product;

    @Override
    public  Product createAbstractProduct() {
        this.product = new Product();
        return product;
    }

    @Override
    public void addVendorCode(String vendorCode) {
      product.setVendorCode(vendorCode);
    }
}
