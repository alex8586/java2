package lv.javaguru.java2.businesslogic.serviceexception;

public interface Error {
    boolean isError();

    String getError();

    void setError(String errorMessage);
}
