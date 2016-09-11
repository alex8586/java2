package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.domain.Product;

public interface CountVisitService {

    void countVisit(Product product);

    void countVisit(Product product, String ip);
}
