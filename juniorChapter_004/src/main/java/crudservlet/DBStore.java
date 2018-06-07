package crudservlet;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
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
    private static final String PATH ="C:/projects/job4j/juniorChapter_004/src/main/java/crudservlet/config.xml";
    private static final String CREATE = "create table if not exists users ("
            + "id serial primary key,"
            + "name character varying (500),"
            + "login character varying (500),"
            + "email character varying (500),"
            + "password character varying (500),"
            + "role character varying (500),"
            + "create_date date"
            + ");";
    private static final DBStore INSTANCE = new DBStore();
    private final BasicDataSource bds = new BasicDataSource();
    private Config config;

    private DBStore() {

    }


    public static DBStore getInstance() {
        return INSTANCE;
    }

    public void init() {
        this.config = this.loadConfig();
        this.bds.setDriverClassName(this.config.getDriverName());
        this.bds.setUrl(this.config.getUrl());
        this.bds.setUsername(this.config.getLogin());
        this.bds.setPassword(this.config.getPassword());

        this.bds.setMinIdle(this.config.getMinIdle());
        this.bds.setMaxIdle(this.config.getMaxIdle());
        this.bds.setMaxOpenPreparedStatements(this.config.getMaxPS());
        this.createDb();
    }

    private Config loadConfig() {
        File file = new File(PATH);
        JAXBContext jaxbContext = null;
        Config result = null;
        try {
            jaxbContext = JAXBContext.newInstance(Config.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            result = (Config) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return result;
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
        try (Connection connection = this.bds.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert  into users(name, login, email, create_date, password, role) values (?, ?, ?, ?, ?, ?) ")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.setDate(4, Date.valueOf(user.getCreateDate()));
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getRole());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(long id, String name, String login, String email, String password, String role) {
        try (Connection connection = this.bds.getConnection();
            PreparedStatement ps = connection.prepareStatement("update users set name = ?, login = ?, email = ?, password = ?, role = ? where id = ?")) {
            ps.setString(1, name);
            ps.setString(2, login);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, role);
            ps.setLong(6, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = this.bds.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from users where id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try (Connection connection = this.bds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users")) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setPassword(rs.getString("password"));
                if (rs.getDate("create_date") != null) {
                    user.setCreateDate(rs.getDate("create_date").toLocalDate());
                }
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
        try (Connection connection = this.bds.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from users where id = ?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                    user.setLogin(rs.getString("login"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));
                    user.setPassword(rs.getString("password"));
                    if (rs.getDate("create_date") != null) {
                        user.setCreateDate(rs.getDate("create_date").toLocalDate());
                    }
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

    @Override
    public User signin(String login, String password) {
        User user = null;
        try (Connection connection = this.bds.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from users where login = ? and password = ?")) {
            ps.setString(1, login);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                    user.setLogin(rs.getString("login"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));
                    user.setPassword(rs.getString("password"));
                    if (rs.getDate("create_date") != null) {
                        user.setCreateDate(rs.getDate("create_date").toLocalDate());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
