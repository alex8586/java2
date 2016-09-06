package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.database.ShippingProfileDAO;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserProvider userProvider;
    @Qualifier("ORM_ShippingProfileDAO")
    @Autowired
    private ShippingProfileDAO shippingProfileDAO;
    @Autowired
    private SpecialSaleOffer specialSaleOffer;

    @Override
    public Map<String, Object> getOrder() {
        Map<String, Object> orderService = new HashMap<>();

        Product product = specialSaleOffer.getOffer();
        orderService.put("saleOffer", product);

        User user = userProvider.getUser();
        if (user != null) {
            List<ShippingProfile> shippingProfiles = shippingProfileDAO.getAllByUser(user);
            orderService.put("shippingProfiles", shippingProfiles);
        }
        orderService.put("user", user);
        return orderService;
    }
}
