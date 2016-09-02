package lv.javaguru.java2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "users_counter")
public class CountUser extends CountBaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name = "user_id")
    private long userId;

    public CountUser(){
        super();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long user_id) {
        this.userId = user_id;
    }
}
