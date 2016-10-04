package lv.javaguru.java2.crossdomain;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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


    @Column(name = "user_visits", columnDefinition = "decimal")
    private long userVisits;

    @Column(name = "visitor_visits", columnDefinition = "decimal")
    private long visitorVisits;

    @Column(name = "avg_rate", columnDefinition = "decimal", precision = 18)
    private double avgRate;

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

    public long getUserVisits() {
        return userVisits;
    }

    public void setUserVisits(long userVisits) {
        this.userVisits = userVisits;
    }

    public long getVisitorVisits() {
        return visitorVisits;
    }

    public void setVisitorVisits(long visitorVisits) {
        this.visitorVisits = visitorVisits;
    }

    public double getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(double avgRate) {
        this.avgRate = avgRate;
    }
}
