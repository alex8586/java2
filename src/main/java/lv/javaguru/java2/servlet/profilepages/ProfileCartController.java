package lv.javaguru.java2.servlet.profilepages;

import lv.javaguru.java2.businesslogic.TemplateService;
import lv.javaguru.java2.businesslogic.user.UserProvider;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProfileCartController extends MVCController {

    @Autowired
    UserProvider userProvider;
    @Autowired
    TemplateService templateService;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        if (!userProvider.authorized())
            return redirectTo(FrontPageController.class);

        Map<String, Object> map = new HashMap<String, Object>();
        User user = userProvider.getUser();
        map.putAll(templateService.model(user));
        return new MVCModel(map, "/profile_cart.jsp");
    }

    @Override
    public MVCModel executePost(HttpServletRequest request) {
        return redirectTo(FrontPageController.class);
    }

}
