package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.adminka.AdminPageDataService;
import lv.javaguru.java2.businesslogic.adminka.ProductTableFactory;
import lv.javaguru.java2.database.hybernate.ProductORMDAOImpl;
import lv.javaguru.java2.database.jdbc.ProductDAOImpl;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Maksims on 8/31/2016.
 */

@Component
@Controller
public class AdminPageController extends MVCController {
//@Autowired
//    ProductORMDAOImpl productDAO;
    @Autowired
    AdminPageDataService dataService;


    @Override
    public MVCModel executeGet(HttpServletRequest request) {
      request.setAttribute("productScene","product_edit.jsp");
        
        dataService.init(request);

//        Map<String, Object> map = new HashMap<>();
//tableFactory.setList(productDAO.getAll());
//        map.put("tabrows",tableFactory.getTable());

        return new MVCModel(dataService.getDataAsMap(), "/admin.jsp");
    }

//    @Override
//    protected MVCModel executePost(HttpServletRequest request) {
//
//
//        return redirectTo(ProfileController.class);
//    }
}
