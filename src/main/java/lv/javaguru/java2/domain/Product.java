package lv.javaguru.java2.domain;

public class Product extends BaseEntity {

    private long categoryID;
    private String vendorCode;
    private String vendorName;
    private String vendorDescription;
    private String unit;
    private int price;
    private String displayName;
    private String displayDescription;
    private int remainQty;
    private String imgUrl;

    public Product() {
    }

    public Product(String vendorCode, String displayName) {
        this.vendorCode = vendorCode;
        this.displayName = displayName;
    }

    public Product(long categoryID, String vendorCode, String vendorName, String vendorDescription,
                   String unit, int price, String displayName, String displayDescription, int remainQty) {
        this.categoryID = categoryID;
        this.vendorCode = vendorCode;
        this.vendorName = vendorName;
        this.vendorDescription = vendorDescription;
        this.unit = unit;
        this.price = price;
        this.displayName = displayName;
        this.displayDescription = displayDescription;
        this.remainQty = remainQty;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (categoryID != product.categoryID) return false;
        if (price != product.price) return false;
        if (remainQty != product.remainQty) return false;
        if (!vendorCode.equals(product.vendorCode)) return false;
        if (vendorName != null ? !vendorName.equals(product.vendorName) : product.vendorName != null) return false;
        if (vendorDescription != null ? !vendorDescription.equals(product.vendorDescription) : product.vendorDescription != null)
            return false;
        if (unit != null ? !unit.equals(product.unit) : product.unit != null) return false;
        if (!displayName.equals(product.displayName)) return false;
        return !(displayDescription != null ? !displayDescription.equals(product.displayDescription) : product.displayDescription != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (categoryID ^ (categoryID >>> 32));
        result = 31 * result + vendorCode.hashCode();
        result = 31 * result + (vendorName != null ? vendorName.hashCode() : 0);
        result = 31 * result + (vendorDescription != null ? vendorDescription.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + displayName.hashCode();
        result = 31 * result + (displayDescription != null ? displayDescription.hashCode() : 0);
        result = 31 * result + remainQty;
        return result;
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
