package lv.javaguru.java2.businesslogic.notification;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class SessionNotificationTest {

    private SessionNotification notification;

    @Before
    public void before() {
        notification = new SessionNotification();
    }

    @Test
    public void testNoNotificationAtStart() {
        assertFalse(notification.haveMessage());
        assertFalse(notification.haveError());
    }

    @Test
    public void testNotificationWithError() {
        notification.setError("error");
        assertTrue(notification.haveError());
        assertFalse(notification.haveMessage());
        notification.getError();
        assertFalse(notification.haveError());
    }

    @Test
    public void testNotificationWithMessage() {
        notification.setMessage("info");
        assertFalse(notification.haveError());
        assertTrue(notification.haveMessage());
        notification.getMessage();
        assertFalse(notification.haveMessage());
    }

    @Test
    public void testCantHaveBothStatusesAtSameTime() {
        notification.setMessage("info");
        assertFalse(notification.haveError());
        assertTrue(notification.haveMessage());

        notification.setError("error");
        assertTrue(notification.haveError());
        assertFalse(notification.haveMessage());
    }


}