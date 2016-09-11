package lv.javaguru.java2.domain;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(name = "user_id")
    public long userId;

    @Column(name = "product_id")
    public long productId;

    @Column(name = "rate")
    public int rate;

    @Column(name = "comment")
    public String comment;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", rate=" + rate +
                ", comment='" + comment + '\'' +
                '}';
    }
}
