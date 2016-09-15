package lv.javaguru.java2.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reviews")
public class Review implements BaseEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(name = "user_id")
    public long userId;

    @Column(name = "product_id")
    public long productId;

    @Column(name = "comment")
    public String comment;

    @Column(name = "user_name")
    public String userName;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    public Date date;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                '}';
    }
}