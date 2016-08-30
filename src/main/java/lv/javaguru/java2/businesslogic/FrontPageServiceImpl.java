package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Category;
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
    @Qualifier("ORM_CategoryDAO")
    CategoryDAO categoryDAO;

    @Autowired
    @Qualifier("ORM_ProductDAO")
    ProductDAO productDAO;

    @Autowired
    @Qualifier("randomSaleOffer")
    private SpecialSaleOffer specialSaleOffer;

    public Map<String, Object> serve(Category category) {
        Map<String, Object> frontPageData = new HashMap<String, Object>();
        frontPageData.put("user", userProvider.getUser());
        frontPageData.put("categories", categoryDAO.getAll());
        
        if (category == null)
            frontPageData.put("products", productDAO.getAll());
        else
            frontPageData.put("products", productDAO.getAllByCategory(category));

        String imgPath = "miskaweb/img/default.jpg";
        Product product = specialSaleOffer.getOffer();
        if (product != null)
            imgPath = product.getImgUrl();
        frontPageData.put("imgPath", imgPath);
        System.out.println("model imagepath = " + imgPath);
        return frontPageData;
    }
}
