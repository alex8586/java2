package lv.javaguru.java2.businesslogic;

public interface StockValidationService {

    boolean isValid(int quantity, long productId);
}
