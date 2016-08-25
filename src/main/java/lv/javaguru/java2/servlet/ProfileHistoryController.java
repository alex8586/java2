package lv.javaguru.java2.servlet;

import lv.javaguru.java2.domain.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProfileHistoryController extends MVCController {

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            return new MVCModel("/index");
        }

        User user = (User) request.getSession().getAttribute("user");
        Map<String, Object> map = new HashMap<String, Object>();

        if (request.getSession().getAttribute("profileError") != null) {
            String error = (String) request.getSession().getAttribute("profileError");
            request.getSession().removeAttribute("profileError");

            map.put("profileError", error);
            map.put("user", user);
            return new MVCModel(map, "/profile_history.jsp");
        }
        map.put("user", user);

        return new MVCModel(map, "/profile_history.jsp");
    }

    @Override
    public MVCModel executePost(HttpServletRequest request) {
        return new MVCModel("/index");
    }

}
