package control.repository;

import control.repository.entity.Address;
import control.repository.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * class AddressDao.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 29.06.2018
 */
public class AddressDao implements CrudDao<Address> {
    private final ConnectionManager manager = ConnectionManager.getInstance();

    @Override
    public List<Address> findAll() {
        List<Address> result = new ArrayList<>();
        try (Connection con = this.manager.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("select * from address;")) {
            while (rs.next()) {
                Address address = new Address();
                address.setId(rs.getLong("id"));
                address.setAddr(rs.getString("addr"));
                result.add(address);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Address add(Address object) {
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("insert into address(addr) values (?);", Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, object.getAddr());
            pst.executeUpdate();
            try (ResultSet key  = pst.getGeneratedKeys()) {
                if (key.next()) {
                    object.setId(key.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public void update(Address object) {
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("update address set addr = ? where id = ?;")) {
            pst.setString(1, object.getAddr());
            pst.setLong(2, object.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("delete from address where id = ?;")) {
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Address findById(long id) {
        Address result = null;
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("select * from address where id = ?;")) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    result = new Address();
                    result.setId(rs.getLong("id"));
                    result.setAddr(rs.getString("addr"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
