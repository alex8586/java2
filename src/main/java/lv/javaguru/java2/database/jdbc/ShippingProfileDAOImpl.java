package lv.javaguru.java2.database.jdbc;


import lv.javaguru.java2.domain.ShippingProfile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class ShippingProfileDAOImpl extends DAOImpl<ShippingProfile> {

    private final String CREATE_NEW = "INSERT INTO shipping_profile(person,document,address,phone,userID_FK,id) VALUES " + " (?,?,?,?,?,DEFAULT)";
    private final String UPDATE_BY_ID = "UPDATE shipping_profile SET person=?,document=?,address=?,phone=?,userID_FK=? WHERE id=?";
    private final String DELETE_BY_ID = "DELETE FROM shipping_profile WHERE id=?";
    private final String GET_BY_ID = "SELECT * FROM shipping_profile WHERE id=?";
    private final String GET_ALL = "SELECT * FROM shipping_profile";

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, ShippingProfile shippingProfile) throws SQLException {
        preparedStatement.setString(1, shippingProfile.getPerson());
        preparedStatement.setString(2, shippingProfile.getDocument());
        preparedStatement.setString(3, shippingProfile.getAddress());
        preparedStatement.setString(4, shippingProfile.getPhone());
        preparedStatement.setLong(5, shippingProfile.getUserId());
        if (shippingProfile.getId() != 0)
            preparedStatement.setLong(6, shippingProfile.getId());
    }

    @Override
    public long create(ShippingProfile shippingProfile) {
        return super.create(shippingProfile, CREATE_NEW);
    }

    @Override
    public void update(ShippingProfile shippingProfile) {
        super.update(shippingProfile, UPDATE_BY_ID);
    }

    @Override
    public void delete(ShippingProfile shippingProfile) {
        super.delete(shippingProfile, DELETE_BY_ID);
    }

    @Override
    protected ShippingProfile buildFromResultSet(ResultSet resultSet) throws SQLException {
        ShippingProfile shippingProfile = new ShippingProfile();
        shippingProfile.setId(resultSet.getLong("id"));
        shippingProfile.setAddress(resultSet.getString("address"));
        shippingProfile.setDocument(resultSet.getString("document"));
        shippingProfile.setPerson(resultSet.getString("person"));
        shippingProfile.setPhone(resultSet.getString("phone"));
        return shippingProfile;
    }

    @Override
    public ShippingProfile getById(long id) {
        return (ShippingProfile) getById(id, GET_BY_ID);
    }

    @Override
    public List<ShippingProfile> getAll() {
        return super.getAll(GET_ALL);
    }

}
