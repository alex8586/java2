package lv.javaguru.java2.dto;

import java.util.Date;

public class ProductCard {

    private long productId;
    private String productName;
    private String productDescription;
    private long productPrice;
    private String productImgUrl;

    private long quantity;

    private int stockQuantity;
    private Date stockExpireDate;

    private double averageRate;
    private String rateColorCode;

    private long viewCount;

    public long getProductId() {
        return productId;
    }
    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public long getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImgUrl() {
        return productImgUrl;
    }
    public void setProductImgUrl(String productImgUrl) {
        this.productImgUrl = productImgUrl;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Date getStockExpireDate() {
        return stockExpireDate;
    }
    public void setStockExpireDate(Date stockExpireDate) {
        this.stockExpireDate = stockExpireDate;
    }

    public long getQuantity() {
        return quantity;
    }
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    public String getRateColorCode() {
        return rateColorCode;
    }

    public void setRateColorCode(String rateColorCode) {
        this.rateColorCode = rateColorCode;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public long getAvailable() {
        return stockQuantity - quantity;
    }
}
