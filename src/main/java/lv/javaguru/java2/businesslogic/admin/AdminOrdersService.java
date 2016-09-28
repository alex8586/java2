package lv.javaguru.java2.businesslogic.admin;

import java.util.Map;

public interface AdminOrdersService {

    Map<String, Object> getListOrders();

    void acceptOrder(long id);

}
