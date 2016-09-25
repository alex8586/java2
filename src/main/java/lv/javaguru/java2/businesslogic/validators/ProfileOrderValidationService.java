package lv.javaguru.java2.businesslogic.validators;

public interface ProfileOrderValidationService {

    boolean isValid(long orderId, long userId);

    boolean isValid(long orderId, String securityKey);
}
