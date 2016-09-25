package lv.javaguru.java2.businesslogic.serviceexception;


public class RecordIsNotAvailable extends ServiceException {
    private static final String error = "Record not found";
    public RecordIsNotAvailable() {
        super(error);
    }
}
