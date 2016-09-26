package lv.javaguru.java2.domain;


public interface CountEntity extends BaseEntity {
    void setProductId(long id);

    int getCounter();

    void setCounter(int i);
}
