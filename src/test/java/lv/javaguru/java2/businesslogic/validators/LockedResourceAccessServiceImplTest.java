package lv.javaguru.java2.businesslogic.validators;

import lv.javaguru.java2.domain.interfaces.LockedResource;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;


public class LockedResourceAccessServiceImplTest {

    LockedResourceAccessServiceImpl lockedResourceAccessService;

    @Before
    public void before() {
        lockedResourceAccessService = new LockedResourceAccessServiceImpl();
    }

    @Test
    public void falseWhenKeyNotMatch() {
        LockedResource lockedResource = Mockito.mock(LockedResource.class);
        Mockito.doReturn("234").when(lockedResource).getKey();
        assertFalse(lockedResourceAccessService.validateKey(lockedResource, "123"));
    }

    @Test
    public void trueWhenKeyDoMatch() {
        LockedResource lockedResource = Mockito.mock(LockedResource.class);
        Mockito.doReturn("1234").when(lockedResource).getKey();
        assertTrue(lockedResourceAccessService.validateKey(lockedResource, "1234"));
    }

    @Test
    public void testRandomKeys() {
        String key1 = lockedResourceAccessService.generateKey();
        String key2 = lockedResourceAccessService.generateKey();
        if (key1 == key2) {
            key2 = lockedResourceAccessService.generateKey();
        }
        assertEquals(key1.length(), key2.length());
        assertFalse(key1.equals(key2));

    }

}