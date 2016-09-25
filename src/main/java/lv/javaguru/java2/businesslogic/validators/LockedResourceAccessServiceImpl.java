package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.domain.interfaces.LockedResource;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LockedResourceAccessServiceImpl implements LockedResourceAccessService {

    Random random = new Random();
    int numchars = 50;

    @Override
    public boolean validateKey(LockedResource lockedResource, String key) {
        return lockedResource.getKey().equals(key);
    }

    @Override
    public String generateKey() {
        StringBuffer sb = new StringBuffer();
        while (sb.length() < numchars) {
            sb.append(Integer.toHexString(random.nextInt()));
        }
        return sb.toString().substring(0, numchars);
    }
}
