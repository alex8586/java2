package lv.javaguru.java2.businesslogic.serviceexception;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class SessionError implements Error {

    boolean isError = false;
    String errorMessage = "";

    public boolean isError() {
        return isError;
    }

    public String getError() {
        String error = errorMessage;
        isError = false;
        errorMessage = "";
        return error;
    }

    public void setError(String error) {
        isError = true;
        errorMessage = error;
    }
}
