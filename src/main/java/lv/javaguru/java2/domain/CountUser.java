package lv.javaguru.java2.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
@Table(name = "users_counter")
public class CountUser {

    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name="product_id")
    private long productId;

    @Column(name="counter")
    private int counter;

    public CountUser(){

    }

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

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CountUser countUser = (CountUser) o;

        return new EqualsBuilder()
                .append(id, countUser.id)
                .append(userId, countUser.userId)
                .append(productId, countUser.productId)
                .append(counter, countUser.counter)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(userId)
                .append(productId)
                .append(counter)
                .toHashCode();
    }
}
