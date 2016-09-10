package lv.javaguru.java2.dto.builders;

import lv.javaguru.java2.dto.ShippingDetails;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


public class ShippingDetailsUtilTest {

    private static final String goodAddress = "City Street House";
    private static final String goodPerson = "Name Surname";
    private static final String goodPhone = "123-456-78";
    private static final String goodDocument = "ZZ-123765";

    private ShippingDetails shippingDetails;
    private ShippingDetailsUtil shippingDetailsUtil = new ShippingDetailsUtil();

    @Before
    public void before() {
    }

    @Test(expected = NullPointerException.class)
    public void buildFailsWhenThereAreNullFields() {
        shippingDetails = shippingDetailsUtil.build(goodAddress, goodPerson, goodPhone, null);
    }

    @Test
    public void buildSucceedWhenThereAreNullId() {
        shippingDetails = shippingDetailsUtil.build(null, goodAddress, goodPerson, goodPhone, goodDocument);
        assertNotNull(shippingDetails);
    }

}