package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.database.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ProfileOrderValidationServiceImpl implements ProfileOrderValidationService {

    @Autowired
    private OrderDAO orderDAO;
    @Qualifier("ORM_UserDAO")

    @Override
    public boolean isValid(long orderId, long userId){
        return orderDAO.getById(orderId).getUserId() == userId;
    }
}
