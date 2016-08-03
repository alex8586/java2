package lv.javaguru.java2.domain;

public class Product {

    private long id;
    private long categoryID;

    private String vendorCode;
    private String vendorName;
    private String vendorDescription;

    private String unit;

    private int price;

    private String displayName;
    private String displayDescription;

    private int remainQty;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + id +
                ", categoryID=" + categoryID +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
