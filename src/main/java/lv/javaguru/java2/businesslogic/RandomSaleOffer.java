package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class RandomSaleOffer implements SpecialSaleOffer {

    @Autowired
    @Qualifier("JDBC_ProductDAO")
    private ProductDAO productDAO;

    @Override
    public Product getOffer() {
        Product product = productDAO.getRandomProduct();
        if (product != null)
            return product;
        else
            return null;
    }

    @Override
    public Product getOffer(long id) {
        Product product = productDAO.getRandomProductWithoutCurrentCategoryId(id);
        if(product != null)
            return product;
        else
            return null;
    }


}
