package lv.javaguru.java2.businesslogic;

import java.util.Map;

public interface ProfileOrderService {

    Map<String, Object> getHistoryOrders();

    Map<String, Object> getOrder(long id);
}
