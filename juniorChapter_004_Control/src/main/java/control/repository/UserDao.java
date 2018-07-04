package control.repository;

import control.repository.entity.User;
import control.repository.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * class UserDao.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 29.06.2018
 */
public class UserDao implements CrudDao<User> {
    private final ConnectionManager manager = ConnectionManager.getInstance();
    private final RoleDao roleDao = new RoleDao();
    private final AddressDao addressDao = new AddressDao();
    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try (Connection con = this.manager.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("select * from users;")) {
            while (rs.next()) {
                User user = this.createUser(rs);
                result.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User add(User object) {
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("insert into users(first_name, last_name, login, password, address_id, role_id) values (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, object.getFirstName());
            pst.setString(2, object.getLastName());
            pst.setString(3, object.getLogin());
            pst.setString(4, object.getPassword());
            pst.setLong(5, object.getAddress().getId());
            pst.setLong(6, object.getRole().getId());
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
    public void update(User object) {
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("update users set first_name = ?, last_name = ?, login = ?, password = ?, address_id = ?, role_id = ?  where id = ?;")) {
            pst.setString(1, object.getFirstName());
            pst.setString(2, object.getLastName());
            pst.setString(3, object.getLogin());
            pst.setString(4, object.getPassword());
            pst.setLong(5, object.getAddress().getId());
            pst.setLong(6, object.getRole().getId());
            pst.setLong(7, object.getId());
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("delete from users where id = ?;")) {
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(long id) {
        User result = null;
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("select * from users where id = ?;")) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    result = this.createUser(rs);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<User> findByMusicType(long musicId) {
        List<User> result = new ArrayList<>();
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("select * from users, users_musictype where users.id = users_musictype.user_id and users_musictype.musictype_id = ?;")) {
            pst.setLong(1, musicId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    User user = this.createUser(rs);
                    result.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    public List<User> findByRole(long roleId) {
        List<User> result = new ArrayList<>();
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("select * from users, role where users.role_id = role.id and role.id = ?;")) {
            pst.setLong(1, roleId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    User user = this.createUser(rs);
                    result.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<User> findByAddress(String address) {
        List<User> result = new ArrayList<>();
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("select * from users, address where users.address_id = address.id and address.addr like ?;")) {
            pst.setString(1, address);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    User user = this.createUser(rs);
                    result.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private User createUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setRole(this.roleDao.findById(rs.getLong("role_id")));
        user.setAddress(this.addressDao.findById(rs.getLong("address_id")));
        return user;
    }

    public User findByLoginPassword(String login, String password) {
        User result = null;
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("select * from users where login = ? and password = ?;")) {
            pst.setString(1, login);
            pst.setString(2, password);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    result = this.createUser(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
