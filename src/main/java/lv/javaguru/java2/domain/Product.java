package lv.javaguru.java2.domain;

import java.util.List;

public class Product {

    private long productID;
    private long categoryID;

    private String vendorCode;
    private String vendorName;
    private String vendorDescription;

    private String unit;

    private int price;

    private String displayName;
    private String displayDescription;

    private int remainQty;

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", categoryID=" + categoryID +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
