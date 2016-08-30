package lv.javaguru.java2.businesslogic;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class RandomSaleOffer implements SpecialSaleOffer {

    ProductDAO productDAO;
    private List<Product> products;
    private Random random = new Random();
    @Autowired
    public RandomSaleOffer(@Qualifier("ORM_ProductDAO") ProductDAO productDAO) {
        this.productDAO = productDAO;
        revolve();
    }

    @Override
    public Product getOffer() {
        if (products.size() > 0)
            return products.get(random.nextInt(products.size()));
        else
            return null;
    }

    private void revolve() {
        products = productDAO.getAll();
    }
}
