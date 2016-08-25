package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class RandomSaleOffer implements SpecialSaleOffer {

    @Autowired
    ProductDAO productDAO;

    private List<Product> products;
    private Random random = new Random();

    public RandomSaleOffer() {
        revolve();
    }

    @Override
    public Product getOffer() {
        return products.get(random.nextInt(products.size()));
    }

    private void revolve() {
        products = productDAO.getAll();
    }
}
