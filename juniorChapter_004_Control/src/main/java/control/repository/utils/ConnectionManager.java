package control.repository.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * class ConnectionManager.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 28.06.2018
 */
public class ConnectionManager {
    private static final ConnectionManager INSTANCE = new ConnectionManager();
    private static final String PATH = "C:/projects/job4j/juniorChapter_004_Control/src/main/java/control/repository/utils/config.xml";
    private final BasicDataSource bds = new BasicDataSource();
    private Config config;

    private ConnectionManager() {
        this.init();
    }

    public static ConnectionManager getInstance() {
        return INSTANCE;
    }
    private void init() {
        this.config = this.loadConfig();
        this.bds.setDriverClassName(this.config.getDriverName());
        this.bds.setUrl(this.config.getUrl());
        this.bds.setUsername(this.config.getLogin());
        this.bds.setPassword(this.config.getPassword());

        this.bds.setMinIdle(this.config.getMinIdle());
        this.bds.setMaxIdle(this.config.getMaxIdle());
        this.bds.setMaxOpenPreparedStatements(this.config.getMaxPS());

    }

    public Connection getConnection() throws SQLException {
        return this.bds.getConnection();
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




    public void close() {
        try {
            this.bds.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
