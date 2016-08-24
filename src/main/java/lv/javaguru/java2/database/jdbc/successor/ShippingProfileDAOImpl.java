package lv.javaguru.java2.database.jdbc.successor;

import lv.javaguru.java2.database.ShippingProfileDAO;
import lv.javaguru.java2.domain.ShippingProfile;
import lv.javaguru.java2.domain.User;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component("JDBC_ShippingProfileDAO")
public class ShippingProfileDAOImpl extends DAOImpl<ShippingProfile> implements ShippingProfileDAO {

    private final String CREATE_NEW = "INSERT INTO shipping_profiles (person, document, address, phone, user_id, id) VALUES " + " (?, ?, ?, ?, ?, DEFAULT)";
    private final String UPDATE_BY_ID = "UPDATE shipping_profiles SET person = ?, document = ?, address = ?, phone = ?, user_id= ? WHERE id = ?";
    private final String DELETE_BY_ID = "DELETE FROM shipping_profiles WHERE id = ?";
    private final String GET_BY_ID = "SELECT * FROM shipping_profiles WHERE id = ?";
    private final String GET_ALL = "SELECT * FROM shipping_profiles";
    private final String GET_BY_USER = "SELECT * FROM shipping_profiles WHERE user_id = ?";

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
    public ShippingProfile getById(long id) {
        return (ShippingProfile) super.getById(id, GET_BY_ID);
    }

    @Override
    public List<ShippingProfile> getAll() {
        return super.getAll(GET_ALL);
    }

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
    protected ShippingProfile buildFromResultSet(ResultSet resultSet) throws SQLException {
        ShippingProfile shippingProfile = new ShippingProfile();
        shippingProfile.setId(resultSet.getLong("id"));
        shippingProfile.setAddress(resultSet.getString("address"));
        shippingProfile.setDocument(resultSet.getString("document"));
        shippingProfile.setPerson(resultSet.getString("person"));
        shippingProfile.setPhone(resultSet.getString("phone"));
        shippingProfile.setUserId(resultSet.getLong("user_id"));
        return shippingProfile;
    }

    public List<ShippingProfile> getAllByUser(User user) {
        return super.getAllByFK(GET_BY_USER, user);
    }
}
