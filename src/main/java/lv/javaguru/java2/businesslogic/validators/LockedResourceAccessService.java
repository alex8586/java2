package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.domain.interfaces.LockedResource;


public interface LockedResourceAccessService {
    boolean validateKey(LockedResource lockedResource, String key);

    String generateKey();
}
