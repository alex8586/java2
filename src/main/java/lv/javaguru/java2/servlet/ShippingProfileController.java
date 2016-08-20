package lv.javaguru.java2.servlet;


import lv.javaguru.java2.database.jdbc.ShippingProfileDAOImpl;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingProfileController extends MVCController {
    private final String EMPTY_FIELDS = "All fields must be filled";

    private ShippingProfileDAOImpl shippingProfileDAO;

    public ShippingProfileController(ShippingProfileDAOImpl shippingProfileDAO) {
        this.shippingProfileDAO = shippingProfileDAO;
    }

    @Override
    public MVCModel doGet(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return new MVCModel(LoginController.getLinkTo());
        }
        Map<String, Object> map = new HashMap<String, Object>();
        List<ShippingProfile> shippingProfiles = shippingProfileDAO.getAllByUser(user);
        ShippingProfile profile = new ShippingProfile();
        profile.setAddress("address");
        profile.setPerson("person");
        shippingProfiles.add(profile);
        map.put("shippingProfiles", shippingProfiles);
        map.put("user", user);
        return new MVCModel(map, "/shippingProfiles.jsp");
    }

    @Override
    public MVCModel doPost(HttpServletRequest request) {
        String name = request.getParameter("address");
        String email = request.getParameter("person");
        String password = request.getParameter("phone");
        String document = request.getParameter("document");
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || document.isEmpty()) {
            request.getSession().setAttribute("profileError", EMPTY_FIELDS);
            return new MVCModel(ShippingProfileController.getLinkTo());
        }

        long id = Long.valueOf(request.getParameter("profileId"));
    }
}
