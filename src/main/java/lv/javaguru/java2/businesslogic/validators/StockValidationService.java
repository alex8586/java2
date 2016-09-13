package lv.javaguru.java2.businesslogic.validators;

public interface StockValidationService {

    boolean isValid(int quantity, long productId);
}
