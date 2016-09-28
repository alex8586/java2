package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.businesslogic.admin.AdminOrdersService;
import lv.javaguru.java2.businesslogic.notification.Notification;
import lv.javaguru.java2.businesslogic.product.SpecialSaleOffer;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    Notification notification;

    @Autowired
    @Qualifier("randomSaleOffer")
    private SpecialSaleOffer specialSaleOffer;

    @Autowired
    private UserProvider userProvider;

    @Autowired
    private AdminOrdersService adminOrdersService;

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
        map.put("user", user);
        int newOrders = adminOrdersService.getNewOrder();
        map.put("newOrders",newOrders);
        return map;
    }


}
