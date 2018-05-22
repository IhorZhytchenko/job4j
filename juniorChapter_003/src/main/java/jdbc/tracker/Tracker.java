package jdbc.tracker;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * class Tracker.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 21.05.2018
 */
public class Tracker implements AutoCloseable {
    private static final String PATH ="juniorChapter_003/src/main/java/jdbc/tracker/config.xml";
    private Connection conn ;
    private Statement statement;
    private PreparedStatement addStm;
    private PreparedStatement updateStm;
    private PreparedStatement deleteStm;
    private PreparedStatement getByNameStm;
    private PreparedStatement getByIdStm;
    private Config config;

    public void connect() {
        try {
            this.config = this.loadConfig();
            this.conn = DriverManager.getConnection(this.config.getUrl(), this.config.getLogin(), this.config.getPassword());
            this.statement = this.conn.createStatement();
            this.statement.execute(this.config.getCreateTable());
            this.addStm = this.conn.prepareStatement(this.config.getAddItem(),Statement.RETURN_GENERATED_KEYS);
            this.updateStm = this.conn.prepareStatement(this.config.getUpdateItem());
            this.deleteStm = this.conn.prepareStatement(this.config.getDeleteItem());
            this.getByIdStm = this.conn.prepareStatement(this.config.getFindById());
            this.getByNameStm = this.conn.prepareStatement(this.config.getFindByName());

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Item add(Item item) {
        try {
            this.addStm.setString(1,item.getName());
            this.addStm.setString(2,item.getDescription());
            this.addStm.setTimestamp(3,item.getCreateDate());
            this.addStm.executeUpdate();
            try (ResultSet key = this.addStm.getGeneratedKeys()) {
                if (key.next()) {
                    item.setId(key.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    public void replace(long id, Item item) {
        try {
            this.updateStm.setString(1,item.getName());
            this.updateStm.setString(2,item.getDescription());
            this.updateStm.setTimestamp(3,item.getCreateDate());
            this.updateStm.setLong(4,id);
            this.updateStm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void delete(long id) {
        try {
            this.deleteStm.setLong(1,id);
            this.deleteStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (ResultSet rs = this.statement.executeQuery(this.config.getGetAllItems())) {
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getLong("id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setCreateDate(rs.getTimestamp("date"));
                result.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try  {
            this.getByNameStm.setString(1,key);
            try (ResultSet rs = this.getByNameStm.executeQuery()){
                while (rs.next()) {
                    Item item = new Item();
                    item.setId(rs.getLong("id"));
                    item.setName(rs.getString("name"));
                    item.setDescription(rs.getString("description"));
                    item.setCreateDate(rs.getTimestamp("date"));
                    result.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Item findById(long id) {
        Item result = null;
        try  {
            this.getByIdStm.setLong(1,id);
            try (ResultSet rs = this.getByIdStm.executeQuery()){
                if (rs.next()) {
                    Item item = new Item();
                    item.setId(rs.getLong("id"));
                    item.setName(rs.getString("name"));
                    item.setDescription(rs.getString("description"));
                    item.setCreateDate(rs.getTimestamp("date"));
                    result = item;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Config loadConfig() throws JAXBException {
        File file = new File(PATH);
        JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Config result = (Config) jaxbUnmarshaller.unmarshal(file);

        return result;
    }


    @Override
    public void close() throws Exception {
        this.statement.close();
        this.addStm.close();
        this.updateStm.close();
        this.deleteStm.close();
        this.getByIdStm.close();
        this.getByNameStm.close();
        this.conn.close();
    }
}
