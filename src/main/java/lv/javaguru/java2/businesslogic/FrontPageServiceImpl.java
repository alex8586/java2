package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FrontPageServiceImpl implements FrontPageService {

    @Autowired
    UserProvider userProvider;

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    @Qualifier("randomSaleOffer")
    private SpecialSaleOffer specialSaleOffer;

    public Map<String, Object> serve() {
        Map<String, Object> frontPageData = new HashMap<String, Object>();
        frontPageData.put("user", userProvider.getUser());
        frontPageData.put("categories", categoryDAO.getAll());
        frontPageData.put("products", productDAO.getAll());

        String imgPath = "miskaweb/img/default.jpg";
        Product product = specialSaleOffer.getOffer();
        if (product != null)
            imgPath = product.getImgUrl();
        frontPageData.put("imgPath", imgPath);
        System.out.println("model imagepath = " + imgPath);
        return frontPageData;
    }
}
