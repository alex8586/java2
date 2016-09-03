package lv.javaguru.java2.domain;

import javax.persistence.*;
import java.io.Serializable;


public abstract class CountBaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="product_id")
    private long product;

    @Column(name="counter")
    private int counter;

    public CountBaseEntity(){
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return product;
    }

    public void setProductId(long product) {
        this.product = product;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
