package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.product.SpecialSaleOffer;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.database.OrderDAO;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TemplateServiceImpl implements TemplateService {

    private final static String IN_PROGRESS = "In progress";

    @Autowired
    Notification notification;

    @Autowired
    @Qualifier("randomSaleOffer")
    private SpecialSaleOffer specialSaleOffer;

    @Autowired
    private UserProvider userProvider;

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public Map<String, Object> model() {
        return model(userProvider.getUser());
    }

    @Override
    public Map<String, Object> model(User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("saleOffer", specialSaleOffer.getOffer());
        if (notification.haveError())
            map.put("error", notification.getError());
        if (notification.haveMessage())
            map.put("message", notification.getMessage());
        if(userProvider.getUser() != null) {
            if (user.isAdmin()) {
                map.put("admin", user);
                long newOrders = orderDAO.getCountStatusInProgress(IN_PROGRESS);
                map.put("newOrders", newOrders);
            } else
                map.put("user", user);
        }
        return map;
    }


}
