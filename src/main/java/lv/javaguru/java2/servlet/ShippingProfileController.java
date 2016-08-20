package lv.javaguru.java2.servlet;


import lv.javaguru.java2.database.jdbc.ShippingProfileDAOImpl;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingProfileController extends MVCController {

    private ShippingProfileDAOImpl shippingProfileDAO;

    public ShippingProfileController(ShippingProfileDAOImpl shippingProfileDAO) {
        this.shippingProfileDAO = shippingProfileDAO;
    }

    @Override
    public MVCModel doGet(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return new MVCModel("/login");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        List<ShippingProfile> shippingProfiles = shippingProfileDAO.getAllByUser(user);
        map.put("shippingProfiles", shippingProfiles);
        map.put("user", user);
        return new MVCModel(map, "/shippingProfiles.jsp");
    }

    @Override
    public MVCModel doPost(HttpServletRequest request) {

    }
}
