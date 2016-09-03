package lv.javaguru.java2.domain;

import javax.persistence.*;

@Entity
@Table(name = "visitors_counter")
public class CountVisitor implements BaseEntity{

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
}
