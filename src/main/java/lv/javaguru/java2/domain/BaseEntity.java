package lv.javaguru.java2.domain;

public abstract class BaseEntity {

    protected long id;

    public boolean exists() {
        return id > 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
