package lv.javaguru.java2.crossdomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "product_statistics")
public class StatisticLine {

    @Column(name = "product_id")
    private BigInteger productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "category_id")
    private BigInteger categoryId;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "review_count")
    private BigInteger reviewCount;
    @Column(name = "avg_rate")
    private BigDecimal avgRate;
    @Column(name = "user_visits")
    private BigDecimal userVisits;
    @Column(name = "visitor_visits")
    private BigDecimal visitorVisits;

    public BigInteger getProductId() {
        return productId;
    }

    public void setProductId(BigInteger productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigInteger getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(BigInteger categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigInteger getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(BigInteger reviewCount) {
        this.reviewCount = reviewCount;
    }

    public BigDecimal getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(BigDecimal avrRate) {
        this.avgRate = avrRate;
    }

    public BigDecimal getUserVisits() {
        return userVisits;
    }

    public void setUserVisits(BigDecimal userVisits) {
        this.userVisits = userVisits;
    }

    public BigDecimal getVisitorVisits() {
        return visitorVisits;
    }

    public void setVisitorVisits(BigDecimal visitorVisits) {
        this.visitorVisits = visitorVisits;
    }
}
