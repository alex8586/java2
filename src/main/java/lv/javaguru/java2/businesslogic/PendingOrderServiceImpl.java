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
public class PendingOrderServiceImpl implements PendingOrderService {

    @Autowired
    private UserProvider userProvider;
    @Qualifier("ORM_ShippingProfileDAO")
    @Autowired
    private ShippingProfileDAO shippingProfileDAO;
    @Autowired
    private SpecialSaleOffer specialSaleOffer;
    @Autowired
    private PendingOrder pendingOrder;

    @Override
    public Map<String, Object> serve() {
        Map<String, Object> data = new HashMap<>();

        Product product = specialSaleOffer.getOffer();
        data.put("saleOffer", product);

        User user = userProvider.getUser();
        if (user != null) {
            List<ShippingProfile> shippingProfiles = shippingProfileDAO.getAllByUser(user);
            data.put("shippingProfiles", shippingProfiles);
        }
        data.put("user", user);
        data.put("pendingOrderCart", pendingOrder.getCart());
        return data;
    }
}
