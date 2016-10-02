package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.database.OrderDAO;
import lv.javaguru.java2.domain.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderAccessValidationServiceImpl implements OrderAccessValidationService {

    @Autowired
    LockedResourceAccessService lockedResourceAccessService;
    @Autowired
    private OrderDAO orderDAO;

    @Override
    public boolean isValid(long orderId, long userId){
        Order order = orderDAO.getById(orderId);
        return order != null && order.getUserId() == userId;
    }

    @Override
    public boolean isValid(long orderId, String securityKey) {
        Order order = orderDAO.getById(orderId);
        return lockedResourceAccessService.validateKey(order, securityKey);
    }
}
