package jdbc;

import jdbc.entity.Entry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * class JdbcManager.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 17.05.2018
 */
public class JdbcManager {
    private static final String URL ="jdbc:postgresql://localhost:5432/SQLite";
    private Connection conn ;
    private Statement statement;
    private PreparedStatement pStm;
    private String login;
    private String password;

    public void connect() {
        try {
            this.conn = DriverManager.getConnection(URL, this.login, this.password);
            this.statement = this.conn.createStatement();
            this.createTable();
            this.pStm = this.conn.prepareStatement("insert into TEST(FIELD) values (?);");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFields(int count) {
        try {
            this.statement.execute("delete from TEST;");
            this.conn.setAutoCommit(false);
            for (int i = 1; i <= count; i++) {
                this.pStm.setInt(1,i);
                this.pStm.executeUpdate();
            }
            this.conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                this.conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                this.conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Entry> getAllFields() {
        List<Entry> result = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = this.statement.executeQuery("select * from TEST;");
            while (rs.next()) {
                Entry entry = new Entry();
                entry.setField(rs.getInt("FIELD"));
                result.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    private void createTable() throws SQLException {
        this.statement.execute("create table if not exists TEST (FIELD integer);");
    }

    public void close() {
        try {
            this.statement.close();
            this.pStm.close();
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
