package lv.javaguru.java2.servlet.profilepages;

import lv.javaguru.java2.businesslogic.error.Error;
import lv.javaguru.java2.businesslogic.profilepages.ProfileUpdateService;
import lv.javaguru.java2.businesslogic.serviceexception.ServiceException;
import lv.javaguru.java2.businesslogic.serviceexception.UnauthorizedAccessException;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.dto.UserProfile;
import lv.javaguru.java2.dto.builders.UserProfileUtil;
import lv.javaguru.java2.servlet.LoginController;
import lv.javaguru.java2.servlet.frontpage.FrontPageController;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class ProfileUpdateController extends MVCController {

    @Autowired
    UserProfileUtil userProfileUtil;

    @Autowired
    ProfileUpdateService profileUpdateService;

    @Autowired
    Error error;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        try {
            Map<String, Object> map = profileUpdateService.model();
            return new MVCModel(map, "/profile_update.jsp");
        } catch (UnauthorizedAccessException e) {
            return redirectTo(LoginController.class);
        } catch (ServiceException e) {
            error.setError(e.getMessage());
            return redirectTo(FrontPageController.class);
        }
    }

    @Override
    protected MVCModel executePost(HttpServletRequest request) {
        try {
            UserProfile userProfile = userProfileUtil
                    .build(request.getParameter("fullName"),
                            request.getParameter("email"),
                            request.getParameter("password"),
                            request.getParameter("repeatPassword"));
            profileUpdateService.update(userProfile);
        } catch (NullPointerException e) {
            return new MVCModel("/error");
        } catch (DBException e) {
            return new MVCModel("/error");
        } catch (ServiceException e) {
            error.setError(e.getMessage());
        }
        return redirectTo(ProfileUpdateController.class);
    }
}