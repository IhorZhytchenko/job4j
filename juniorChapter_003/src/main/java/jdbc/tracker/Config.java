package jdbc.tracker;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * class Config.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 21.05.2018
 */
@XmlRootElement(name = "config")
public class Config {
    private String url;
    private String login;
    private String password;
    private String createTable;
    private String addItem;
    private String updateItem;
    private String deleteItem;
    private String getAllItems;
    private String findByName;
    private String findById;

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
    @XmlElement(name = "createTable")
    public String getCreateTable() {
        return createTable;
    }

    public void setCreateTable(String createTable) {
        this.createTable = createTable;
    }
    @XmlElement(name = "addItem")
    public String getAddItem() {
        return addItem;
    }

    public void setAddItem(String addItem) {
        this.addItem = addItem;
    }
    @XmlElement(name = "updateItem")
    public String getUpdateItem() {
        return updateItem;
    }

    public void setUpdateItem(String updateItem) {
        this.updateItem = updateItem;
    }
    @XmlElement(name = "deleteItem")
    public String getDeleteItem() {
        return deleteItem;
    }

    public void setDeleteItem(String deleteItem) {
        this.deleteItem = deleteItem;
    }
    @XmlElement(name = "getAllItems")
    public String getGetAllItems() {
        return getAllItems;
    }

    public void setGetAllItems(String getAllItems) {
        this.getAllItems = getAllItems;
    }
    @XmlElement(name = "findByName")
    public String getFindByName() {
        return findByName;
    }

    public void setFindByName(String findByName) {
        this.findByName = findByName;
    }
    @XmlElement(name = "findById")
    public String getFindById() {
        return findById;
    }

    public void setFindById(String findById) {
        this.findById = findById;
    }


}
