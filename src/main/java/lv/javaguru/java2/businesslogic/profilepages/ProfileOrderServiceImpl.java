package lv.javaguru.java2.businesslogic.profilepages;

import lv.javaguru.java2.businesslogic.TemplateService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.UnauthorizedAccessException;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.database.OrderDAO;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProfileOrderServiceImpl implements ProfileOrderService {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private TemplateService templateService;

    @Override
    public Map<String, Object> getById(long orderId) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderLine", orderDAO.getAllOrderLinesByOrderId(orderId));
        map.put("order", orderDAO.getById(orderId));
        map.putAll(templateService.model());
        return map;
    }

    @Override
    public Map<String, Object> model() throws ServiceException {
        if (!userProvider.authorized()) {
            throw new UnauthorizedAccessException();
        }
        return model(userProvider.getUser());
    }

    @Override
    public Map<String, Object> model(User user) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderList", orderDAO.getByUserId(user.getId()));
        map.putAll(templateService.model());
        return map;
    }


}
