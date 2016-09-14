package lv.javaguru.java2.businesslogic.serviceexception;


public class IllegalRequestException extends ServiceException {
    private static final String error = "Wrong request";
    public IllegalRequestException() {
        super(error);
    }

    protected IllegalRequestException(String error) {
        super(error);
    }
}
