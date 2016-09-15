package lv.javaguru.java2.businesslogic.serviceexception;

import lv.javaguru.java2.domain.Product;


public class InsufficientSupplyException extends ServiceException {
    private static final String error = "Unable to supply cart with product ";

    public InsufficientSupplyException(Product product) {
        super(error + product.getName());
    }
}
