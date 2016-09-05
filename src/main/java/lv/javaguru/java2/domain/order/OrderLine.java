package lv.javaguru.java2.domain.order;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "order_lines")
public class OrderLine {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "order_id")
    private long userId;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private long price;

    @Column(name = "quantity")
    private long quantity;

    @Column(name = "expire_date")
    @Temporal(TemporalType.DATE)
    private Date expireDate;

}
