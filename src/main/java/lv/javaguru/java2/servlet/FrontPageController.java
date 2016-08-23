package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.CategoryDAOImpl;
import lv.javaguru.java2.database.jdbc.ProductDAOImpl;
import lv.javaguru.java2.domain.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FrontPageController extends MVCController {

    private CategoryDAOImpl categoryDAO;
    private ProductDAOImpl productDAO = new ProductDAOImpl();

    public FrontPageController(CategoryDAOImpl categoryDAO, ProductDAOImpl productDAO) {
        this.categoryDAO = categoryDAO;
        this.productDAO = productDAO;
    }

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        Map<String, Object> frontPageData = new HashMap<String, Object>();
        try {
            String imgPath;
            int bannerId;
            List<Product> productList = productDAO.getAll();
            if (productList.size() == 0) {
                imgPath = "miskaweb/img/default.jpg";
            } else {
                Random random = new Random();
                bannerId = random.nextInt(productList.size());
                imgPath = productList.get(bannerId).getImgUrl();
            }

            frontPageData.put("user", request.getSession().getAttribute("user"));
            frontPageData.put("categories", categoryDAO.getAll());
            frontPageData.put("products", productDAO.getAll());
            frontPageData.put("imgPath", imgPath);
        } catch (DBException dbe) {
        }
        return new MVCModel(frontPageData, "/frontpage.jsp");
    }
}
