package lv.javaguru.java2.businesslogic.error;

public interface Error {
    boolean isError();

    String getError();

    void setError(String errorMessage);
}
