package lv.javaguru.java2.businesslogic.admin;

import java.util.Map;

public interface AdminOrdersService {

    Map<String, Object> getListOrders();

    Map<String, Object> getListOrdersSortByStatus();

    void acceptOrder(long id);
}
