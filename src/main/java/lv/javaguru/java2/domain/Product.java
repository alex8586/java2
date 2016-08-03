package lv.javaguru.java2.domain;

import java.util.List;

public class Product {

    private Long productID;
    private Long categoryID;

    private String vendorCode;
    private String vendorName;
    private String vendorDescription;

    private String sellUnit;
    private String buyUnit;

    private Integer unitSellPrice;
    private Integer unitBuyPrice;


    private String displayName;
    private String displayDescription;

    private Float remainUnitQty;

    // experimental
//
//    private List<ProductChanges> changes;
//    private List<Suppliers> suppliers;




    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
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

    public String getSellUnit() {
        return sellUnit;
    }

    public void setSellUnit(String sellUnit) {
        this.sellUnit = sellUnit;
    }

    public String getBuyUnit() {
        return buyUnit;
    }

    public void setBuyUnit(String buyUnit) {
        this.buyUnit = buyUnit;
    }

    public Integer getUnitSellPrice() {
        return unitSellPrice;
    }

    public void setUnitSellPrice(Integer unitSellPrice) {
        this.unitSellPrice = unitSellPrice;
    }

    public Integer getUnitBuyPrice() {
        return unitBuyPrice;
    }

    public void setUnitBuyPrice(Integer unitBuyPrice) {
        this.unitBuyPrice = unitBuyPrice;
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
}
