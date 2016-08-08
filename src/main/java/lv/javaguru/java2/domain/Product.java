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

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorDescription() {
        return vendorDescription;
    }

    public void setVendorDescription(String vendorDescription) {
        this.vendorDescription = vendorDescription;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayDescription() {
        return displayDescription;
    }

    public void setDisplayDescription(String displayDescription) {
        this.displayDescription = displayDescription;
    }

    public int getRemainQty() {
        return remainQty;
    }

    public void setRemainQty(int remainQty) {
        this.remainQty = remainQty;
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
