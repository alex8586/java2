package lv.javaguru.java2.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
@Table(name = "visitors_counter")
public class CountVisitor implements BaseEntity, CountEntity {

    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="product_id")
    private long productId;

    @Column(name="counter")
    private int counter;

    @Column(name = "ip")
    private String ip;

    public CountVisitor(){

    }

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

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CountVisitor that = (CountVisitor) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(productId, that.productId)
                .append(counter, that.counter)
                .append(ip, that.ip)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(productId)
                .append(counter)
                .append(ip)
                .toHashCode();
    }
}
