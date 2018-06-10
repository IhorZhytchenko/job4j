package crudservlet;

import java.util.List;
/**
 * ValidateService.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 29.05.2018
 */
public class ValidateService {
    private static final ValidateService INSTANCE = new ValidateService();
    private final Store logic = DBStore.getInstance();

    private ValidateService() {

    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    public void addAddress(String city, String country) {
        if (!this.logic.containsAddress(city, country)) {
            this.logic.addAddress(city, country);
        }

    }
    public  List<Address> allAddresses() {
        return this.logic.allAddresses();
    }
    public Address addressById(long id) {
        return this.logic.addressById(id);
    }

    public void add(String name, String login, String email, String password, String role, long addressId) {
        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setAdressId(addressId);
        this.logic.add(user);
    }

    public void update(long id, String name, String login, String email, String password, String role, long addressId) {
        if (this.logic.contains(id)) {
            this.logic.update(id, name, login, email, password, role, addressId);
        }
    }

    public void delete(long id) {
        if (this.logic.contains(id)) {
            this.logic.delete(id);
        }
    }

    public List<User> findAll() {
        return this.logic.findAll();
    }

    public User findById(long id) {
        User result = null;
        if (this.logic.contains(id)) {
            result = this.logic.findById(id);
        }
        return result;
    }

    public User signin(String login, String password) {
        return this.logic.signin(login, password);
    }

}
