package lv.javaguru.java2.servlet;

import lv.javaguru.java2.businesslogic.adminka.ProductTableFactory;
import lv.javaguru.java2.database.hybernate.ProductORMDAOImpl;
import lv.javaguru.java2.database.jdbc.ProductDAOImpl;
import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Maksims on 8/31/2016.
 */

@Component

public class AdminPageController extends MVCController {
@Autowired
    ProductORMDAOImpl productDAO;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        map.put("tabrows",new ProductTableFactory(productDAO.getAll()).getRows());

        return new MVCModel(map, "/admin.jsp");
    }

//    @Override
//    protected MVCModel executePost(HttpServletRequest request) {
//
//
//        return redirectTo(ProfileController.class);
//    }
}
