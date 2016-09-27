package lv.javaguru.java2.crossdomain;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Date;

@Immutable
@Entity
@Table(name = "stock_product")
public class StockProduct {

    @Id
    @Column(name = "stock_id", updatable = false, nullable = false)
    private long id;
    @Column(name = "product_id")
    private long productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "quantity")
    private int quantity;
    @Temporal(TemporalType.DATE)
    @Column(name = "expire_date")
    private Date expireDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
