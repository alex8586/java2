package lv.javaguru.java2.dto.builders;

import lv.javaguru.java2.dto.RequestedEntity;
import org.springframework.stereotype.Component;

@Component
public class RequestedEntityUtil {
    public RequestedEntity build(String requestId) {
        if (requestId == null)
            throw new NullPointerException();
        long id = 0;
        try {
            id = Long.valueOf(requestId);
        } catch (NumberFormatException e) {

        }
        return new RequestedEntity(id);
    }
}
