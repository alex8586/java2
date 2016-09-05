package lv.javaguru.java2.domain.order;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "orders")
public class Order {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "person")
    private String person;

    @Column(name = "document")
    private String document;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Column(name = "total")
    private long total;

    @Column(name = "user_id")
    private long userId;

}
