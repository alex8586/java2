package lv.javaguru.java2.businesslogic.product;

import lv.javaguru.java2.domain.Product;
import lv.javaguru.java2.domain.User;

public interface CountVisitService {

    void countVisit(Product product, User user);

    void countVisit(Product product, String ip);
}
