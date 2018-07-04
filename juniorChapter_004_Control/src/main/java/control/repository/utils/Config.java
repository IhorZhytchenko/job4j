package control.repository.utils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * class Config.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 28.06.2018
 */
@XmlRootElement(name = "config")
public class Config {
    private String driverName;
    private String url;
    private String login;
    private String password;
    private int minIdle;
    private int maxIdle;
    private int maxPS;
    @XmlElement(name = "driverName")
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    @XmlElement(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @XmlElement(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    @XmlElement(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @XmlElement(name = "minIdle")
    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }
    @XmlElement(name = "maxIdle")
    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }
    @XmlElement(name = "maxPS")
    public int getMaxPS() {
        return maxPS;
    }

    public void setMaxPS(int maxPS) {
        this.maxPS = maxPS;
    }


}
