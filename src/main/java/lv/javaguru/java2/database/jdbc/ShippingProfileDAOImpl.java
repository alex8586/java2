package lv.javaguru.java2.database.jdbc;


import lv.javaguru.java2.database.DAO;
import lv.javaguru.java2.domain.ShippingProfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/*
 id        INT(11) PRIMARY KEY AUTO_INCREMENT,
  person    VARCHAR(100) NOT NULL,
  document  VARCHAR(50)  NOT NULL,
  address   VARCHAR(100) NOT NULL,
  phone     VARCHAR(15)  NOT NULL,
  userID_FK INT(11),
 */

public class ShippingProfileDAOImpl extends DAOImpl implements DAO<ShippingProfile> {

    private final String CREATE_SHIPPING_PROFILE =
            "INSERT INTO shipping_profile(id,person,document,address,phone,userID_FK) VALUES " + " (DEFAULT ,?,?,?,?,?)";
    private final String UPDATE_SHIPPING_PROFILE =
            "UPDATE shipping_profile SET person=?,document=?,address=?,phone=?,userID_FK=? WHERE id=?";
    private final String DELETE_SHIPPING_PROFILE =
            "DELETE FROM shipping_profile WHERE id=?"

    @Override
    public long create(ShippingProfile shippingProfile) {
        long newId = 0;
        if (shippingProfile == null || shippingProfile.getId() != 0)
            throw new IllegalArgumentException("Exception while execute ShippingProfile.create . Input id != 0 ");

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    (CREATE_SHIPPING_PROFILE, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, shippingProfile.getPerson());
            preparedStatement.setString(2, shippingProfile.getDocument());
            preparedStatement.setString(3, shippingProfile.getAddress());
            preparedStatement.setString(4, shippingProfile.getPhone());
            preparedStatement.setLong(5, shippingProfile.getUserId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                newId = rs.getLong(1);
                shippingProfile.setId(newId);
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute ShippingProfileDAOImpl.create()");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return newId;
    }

    @Override
    public void update(ShippingProfile shippingProfile) {
        if (shippingProfile == null || shippingProfile.getId() == 0)
            throw new IllegalArgumentException("Exception while execute ShippingProfileDAOImpl.update . Input id != 0 ");

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(UPDATE_SHIPPING_PROFILE);

            preparedStatement.setString(1, shippingProfile.getPerson());
            preparedStatement.setString(2, shippingProfile.getDocument());
            preparedStatement.setString(3, shippingProfile.getAddress());
            preparedStatement.setString(4, shippingProfile.getPhone());
            preparedStatement.setLong(5, shippingProfile.getUserId());

            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() != 1) {
                throw new IllegalStateException("Exception while execute ShippingProfileDAOImpl.update - 0 or more than 1 record updated");
            }
        } catch (Throwable e) {
            System.out.println("Exception while updating shipping profile");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(ShippingProfile shippingProfile) {
        if (shippingProfile == null || shippingProfile.getId() == 0)
            return;
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(DELETE_SHIPPING_PROFILE);
            preparedStatement.setLong(1, shippingProfile.getId());
            preparedStatement.executeUpdate();
            shippingProfile.setId(0);
        } catch (Throwable e) {
            System.out.println("Exception while deleting shipping_profile");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public ShippingProfile getById(long id) {
        return null;
    }

    @Override
    public List<ShippingProfile> getAll() {
        return null;
    }
}
