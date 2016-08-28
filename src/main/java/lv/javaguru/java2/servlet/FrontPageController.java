package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.CategoryDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class FrontPageController extends MVCController{

    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private ProductDAO productDAO;

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        Map<String, Object> frontPageData = new HashMap<String, Object>();
        try {
            List<Product> productList = null;
            Category category = (Category) request.getSession().getAttribute("currentCategory");
            System.out.println(category);
            if (category == null || category.getId() == 1)
                productList = productDAO.getAll();
            else
                productList = productDAO.getAllByCategory(category);

            String imgPath;
            int bannerId;
            if (productList.size() == 0) {
                imgPath = "miskaweb/img/default.jpg";
            } else {
                Random random = new Random();
                bannerId = random.nextInt(productList.size());
                imgPath = productList.get(bannerId).getImgUrl();
            }

            frontPageData.put("user", request.getSession().getAttribute("user"));
            frontPageData.put("categories", categoryDAO.getAll());
            frontPageData.put("products", productList);
            frontPageData.put("imgPath", imgPath);
        } catch (DBException dbe) {
        }
        return new MVCModel(frontPageData, "/frontpage.jsp");
    }
}
