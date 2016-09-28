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
        List<Order> orderList = orderDAO.getAll();
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

    @Override
    public Map<String, Object> getListOrdersSortByStatus(){
        Map<String, Object> map = new HashMap<>();
        List<Order> orderList = orderDAO.getAll();
        Collections.sort(orderList,(Order o1, Order o2) -> o2.getStatus().compareTo(o1.getStatus()));
        map.put("orderList", orderList);
        map.putAll(templateService.model());
        return map;
    }

    @Override
    public int getNewOrder(){
        List<Order> list = orderDAO.getAll();
        List<Order> listStatusInProgress = new ArrayList<>();
        for(Order order : list){
            if(order.getStatus().equals("In progress"))
                listStatusInProgress.add(order);
        }
        return listStatusInProgress.size();
    }
}
