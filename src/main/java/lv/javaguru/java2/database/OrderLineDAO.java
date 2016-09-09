package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.order.OrderLine;

import java.util.List;

public interface OrderLineDAO extends CrudDAO<OrderLine> {

    List<OrderLine> getAllByOrderId(long id);
}
