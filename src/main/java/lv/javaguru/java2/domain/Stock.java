package lv.javaguru.java2.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "stock")
@FilterDef(name = "fqty", parameters = @ParamDef(name = "qty", type = "int"))
@Filter(name = "fqty", condition = "quantity > :qty")
public class Stock implements BaseEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "expire_date")
    @Temporal(TemporalType.DATE)
    private Date expireDate;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if ((object == null) || !(object instanceof Stock)) {
            return false;
        }
        Stock that = (Stock) object;
        return new EqualsBuilder()
                .append(id, that.getId())
                .append(productId, that.getProductId())
                .append(quantity, that.getQuantity())
                .append(expireDate, that.getExpireDate())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(productId)
                .append(quantity)
                .append(expireDate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", expireDate=" + expireDate +
                '}';
    }
}
