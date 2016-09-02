package lv.javaguru.java2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "visitors_counter")
public class CountVisitor extends CountBaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "ip")
    private String ip;

    public CountVisitor(){
        super();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
