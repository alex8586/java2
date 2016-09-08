package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.order.Order;

import java.util.List;


public interface OrderDAO extends CrudDAO<Order> {

    List<Order> getByUserId(long id);
}
