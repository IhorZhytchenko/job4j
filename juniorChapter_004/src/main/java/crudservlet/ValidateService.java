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
    private final Store logic = MemoreStore.getInstance();

    private ValidateService() {

    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    public void add(String name, String login, String email) {
        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setEmail(email);
        this.logic.add(user);
    }

    public void update(long id, String name, String login, String email) {
        if (this.logic.contains(id)) {
            this.logic.update(id, name, login, email);
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

}
