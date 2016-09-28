package lv.javaguru.java2.businesslogic.admin;

import lv.javaguru.java2.businesslogic.TemplateService;
import lv.javaguru.java2.database.OrderDAO;
import lv.javaguru.java2.domain.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AdminOrdersServiceImpl implements AdminOrdersService {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private TemplateService templateService;

    @Override
    public Map<String, Object> getListOrders(){
        Map<String, Object> map = new HashMap<>();
        List<Order> orderList = orderDAO.getAllSortByStatus();
        map.putAll(templateService.model());
        map.put("orderList", orderList);
        return map;
    }

    @Override
    public void acceptOrder(long id){
        Order order = orderDAO.getById(id);
        order.setStatus(true);
        orderDAO.update(order);
    }

}
