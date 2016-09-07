package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.domain.Product;

public interface SpecialSaleOffer {

    Product getOffer();

    Product getOffer(long id);
}
