package lv.javaguru.java2.crossdomain;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Immutable
@Table(name = "product_statistics")
public class StatisticLine {

    @Id
    @Column(name = "product_id", updatable = false, nullable = false)
    private long productId;

    @Column(name = "product_name")
    private String productName;
    @Column(name = "category_id")
    private long categoryId;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "review_count")
    private long reviewCount;
    @Column(name = "user_visits")
    private BigInteger userVisits;
    @Column(name = "visitor_visits")
    private BigInteger visitorVisits;
    @Column(name = "avg_rate")
    private BigInteger avgRate;

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

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(long reviewCount) {
        this.reviewCount = reviewCount;
    }

    public BigInteger getUserVisits() {
        return userVisits;
    }

    public void setUserVisits(BigInteger userVisits) {
        this.userVisits = userVisits;
    }

    public BigInteger getVisitorVisits() {
        return visitorVisits;
    }

    public void setVisitorVisits(BigInteger visitorVisits) {
        this.visitorVisits = visitorVisits;
    }

    public BigInteger getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(BigInteger avgRate) {
        this.avgRate = avgRate;
    }
}
