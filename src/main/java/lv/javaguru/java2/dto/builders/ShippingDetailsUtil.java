package lv.javaguru.java2.dto.builders;

import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.order.Order;
import lv.javaguru.java2.dto.ShippingDetails;
import org.springframework.stereotype.Component;

@Component
public class ShippingDetailsUtil {

    public ShippingDetails build(String resourceId, String address, String person, String phone, String document) {
        if (address == null || person == null || phone == null || document == null)
            throw new NullPointerException();
        ShippingDetails shippingDetails = new ShippingDetails();
        if (resourceId != null) {
            try {
                long id = Long.valueOf(resourceId);
                shippingDetails.setId(id);
            } catch (Exception e) {

            }
        }
        shippingDetails.setAddress(address);
        shippingDetails.setDocument(document);
        shippingDetails.setPerson(person);
        shippingDetails.setPhone(phone);
        return shippingDetails;
    }

    public ShippingDetails build(String address, String person, String phone, String document) {
        return build("0", address, person, phone, document);
    }

    public ShippingProfile buildShippingProfile(ShippingDetails shippingDetails) {
        ShippingProfile shippingProfile = new ShippingProfile();
        updateShippingProfile(shippingDetails, shippingProfile);
        return shippingProfile;
    }

    public void updateShippingProfile(ShippingDetails shippingDetails, ShippingProfile shippingProfile) {
        shippingProfile.setAddress(shippingDetails.getAddress());
        shippingProfile.setPerson(shippingDetails.getPerson());
        shippingProfile.setPhone(shippingDetails.getPhone());
        shippingProfile.setDocument(shippingDetails.getDocument());
    }

    public void updateOrder(ShippingDetails shippingDetails, Order order) {
        order.setPhone(shippingDetails.getPhone());
        order.setAddress(shippingDetails.getAddress());
        order.setDocument(shippingDetails.getDocument());
        order.setPerson(shippingDetails.getPerson());
    }
}
