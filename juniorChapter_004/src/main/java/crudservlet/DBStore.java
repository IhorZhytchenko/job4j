package crudservlet;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * DBStore.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 31.05.2018
 */
public class DBStore implements Store {
    private static final String CREATE = "create table if not exists users (" +
            "id serial primary key," +
            "name character varying (500)," +
            "login character varying (500)," +
            "email character varying (500)," +
            "create_date date" +
            ");";
    private static final DBStore INSTANCE = new DBStore();
    private final BasicDataSource bds = new BasicDataSource();

    private DBStore() {

    }


    public static DBStore getInstance() {
        return INSTANCE;
    }

    public void init() {
        this.bds.setDriverClassName("org.postgresql.Driver");
        this.bds.setUrl("jdbc:postgresql://localhost:5432/servlet");
        this.bds.setUsername("postgres");
        this.bds.setPassword("220889");

        this.bds.setMinIdle(5);
        this.bds.setMaxIdle(10);
        this.bds.setMaxOpenPreparedStatements(100);
        this.createDb();
    }

    private void createDb() {
        try (Connection connection = this.bds.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(CREATE);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void close() {
        try {
            this.bds.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(User user) {
        try(Connection connection = this.bds.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert  into users(name, login, email, create_date) values (?, ?, ?, ?) ")) {
            ps.setString(1,user.getName());
            ps.setString(2,user.getLogin());
            ps.setString(3,user.getEmail());
            ps.setDate(4,Date.valueOf(user.getCreateDate()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(long id, String name, String login, String email) {
        try(Connection connection = this.bds.getConnection();
            PreparedStatement ps = connection.prepareStatement("update users set name = ?, login = ?, email = ? where id = ?")) {
            ps.setString(1,name);
            ps.setString(2,login);
            ps.setString(3,email);
            ps.setLong(4,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try(Connection connection = this.bds.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from users where id = ?")) {
            ps.setLong(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try(Connection connection = this.bds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users")) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setCreateDate(rs.getDate("create_date").toLocalDate());
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User findById(long id) {
        User user = new User();
        try(Connection connection = this.bds.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from users where id = ?")) {
            ps.setLong(1,id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                    user.setLogin(rs.getString("login"));
                    user.setEmail(rs.getString("email"));
                    user.setCreateDate(rs.getDate("create_date").toLocalDate());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return user;
    }

    @Override
    public boolean contains(long id) {
        return true;
    }
}
