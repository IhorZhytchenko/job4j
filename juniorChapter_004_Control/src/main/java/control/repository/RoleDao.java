package control.repository;

import control.repository.entity.Role;
import control.repository.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * class RoleDao.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 29.06.2018
 */
public class RoleDao implements CrudDao<Role> {
    private final ConnectionManager manager = ConnectionManager.getInstance();

    @Override
    public List<Role> findAll() {
        List<Role> result = new ArrayList<>();
        try (Connection con = this.manager.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("select * from role;")) {
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getLong("id"));
                role.setRoleName(rs.getString("role_name"));
                result.add(role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Role add(Role object) {
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("insert into role(role_name) values (?);", Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, object.getRoleName());
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
    public void update(Role object) {
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("update role set role_name = ? where id = ?;")) {
            pst.setString(1, object.getRoleName());
            pst.setLong(2, object.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("delete from role where id = ?;")) {
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Role findById(long id) {
        Role result = null;
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("select * from role where id = ?;")) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    result = new Role();
                    result.setId(rs.getLong("id"));
                    result.setRoleName(rs.getString("role_name"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
