package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.jdbc.ProductDAOImpl;
import lv.javaguru.java2.domain.Category;
import lv.javaguru.java2.domain.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

class CategoryChooseController extends MVCController {

    ProductDAOImpl productDAO;

    public CategoryChooseController(ProductDAOImpl productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public MVCModel executeGet(HttpServletRequest request) {
        Map<String, Object> categoryChooseData = new HashMap<String, Object>();

        Category category = new Category();
        long categoryId = category.getId();

        String imgPath;
        int bannerId;
        List<Product> productList = productDAO.getAll();
        List<Product> productWithoutCategory = new ArrayList<>();
        for (Product product : productList) {
            if (product.getCategoryID() != categoryId) {
                productWithoutCategory.add(product);
            }
        }
        if (productWithoutCategory.size() == 0) {
            imgPath = "miskaweb/img/default.jpg";
        } else {
            Random random = new Random();
            bannerId = random.nextInt(productWithoutCategory.size());
            imgPath = productList.get(bannerId).getImgUrl();
        }

        categoryChooseData.put("imgPath", imgPath);
        return new MVCModel("/index");
    }

    @Override
    public MVCModel executePost(HttpServletRequest request) {
        return new MVCModel("/index");
    }
}
