package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.User;

import java.util.List;

public interface ShippingProfileDAO extends CrudDAO<ShippingProfile> {
    List<ShippingProfile> getAllByUser(User user);
}
