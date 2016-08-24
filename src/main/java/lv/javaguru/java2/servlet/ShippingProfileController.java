package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.jdbc.successor.ShippingProfileDAOImpl;
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

    public MVCModel executeGet(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return new MVCModel("/login");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        List<ShippingProfile> shippingProfiles = shippingProfileDAO.getAllByUser(user);
        ShippingProfile profile = new ShippingProfile();
        map.put("shippingProfiles", shippingProfiles);

        map.put("user", user);
        return new MVCModel(map, "/shippingProfiles.jsp");
    }

    public MVCModel executePost(HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return new MVCModel("/login");
        }
        ShippingProfile shippingProfile = buildShippingProfileFromRequest(request);
        if (shippingProfile.getAddress().isEmpty() || shippingProfile.getPerson().isEmpty() ||
                shippingProfile.getPhone().isEmpty() || shippingProfile.getDocument().isEmpty()) {
            request.getSession().setAttribute("profileError", EMPTY_FIELDS);
            return new MVCModel("/profile/shippingProfile");
        }
        if (shippingProfile.getUserId() > 0) {
            ShippingProfile oldProfile = shippingProfileDAO.getById(shippingProfile.getId());
            if (oldProfile.getUserId() != user.getId()) {
                throw new IllegalStateException("Unable to access resource");
            }
            shippingProfileDAO.update(shippingProfile);
        } else {
            shippingProfile.setUserId(user.getId());
            shippingProfileDAO.create(shippingProfile);
        }
        return new MVCModel("/profile/shippingProfile");
    }


    private ShippingProfile buildShippingProfileFromRequest(HttpServletRequest request) {
        ShippingProfile shippingProfile = new ShippingProfile();

        String param = request.getParameter("profileId");
        if (param != null && !param.isEmpty()) {
            shippingProfile.setUserId(Long.valueOf(param));
        }
        shippingProfile.setAddress(request.getParameter("address"));
        shippingProfile.setPerson(request.getParameter("person"));
        shippingProfile.setPhone(request.getParameter("phone"));
        shippingProfile.setDocument(request.getParameter("document"));
        return shippingProfile;
    }
}

