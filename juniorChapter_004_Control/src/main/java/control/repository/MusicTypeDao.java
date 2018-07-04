package control.repository;

import control.repository.entity.MusicType;
import control.repository.entity.Role;
import control.repository.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * class MusicTypeDao.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 29.06.2018
 */
public class MusicTypeDao implements CrudDao<MusicType> {
    private final ConnectionManager manager = ConnectionManager.getInstance();
    @Override
    public List<MusicType> findAll() {
        List<MusicType> result = new ArrayList<>();
        try (Connection con = this.manager.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("select * from musictype;")) {
            while (rs.next()) {
                MusicType musicType = new MusicType();
                musicType.setId(rs.getLong("id"));
                musicType.setType(rs.getString("type"));
                result.add(musicType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public MusicType add(MusicType object) {
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("insert into musictype(type) values (?);", Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, object.getType());
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
    public void update(MusicType object) {
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("update musictype set type = ? where id = ?;")) {
            pst.setString(1, object.getType());
            pst.setLong(2, object.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("delete from musictype where id = ?;")) {
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MusicType findById(long id) {
        MusicType result = null;
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("select * from musictype where id = ?;")) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    result = new MusicType();
                    result.setId(rs.getLong("id"));
                    result.setType(rs.getString("type"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public MusicType findByType(String type) {
        MusicType result = null;
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("select * from musictype where type = ?;")) {
            pst.setString(1, type);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    result = new MusicType();
                    result.setId(rs.getLong("id"));
                    result.setType(rs.getString("type"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<MusicType> findByUserId(long id) {
        List<MusicType> result = new ArrayList<>();
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("select * from users_musictype where user_id = ?;")) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                   result.add(this.findById(rs.getLong("musictype_id")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addUserMusic(long userId, long musicId) {
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("insert into users_musictype(user_id, musictype_id) values (?, ?);")) {
            pst.setLong(1, userId);
            pst.setLong(2, musicId);
            pst.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteByUserId(long userId) {
        try (Connection con = this.manager.getConnection();
             PreparedStatement pst = con.prepareStatement("delete from users_musictype where user_id = ?;")) {
            pst.setLong(1, userId);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
