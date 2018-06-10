package crudservlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
/**
 * MemoreStore.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 29.05.2018
 */
public class MemoreStore implements Store {
    private static final MemoreStore INSTANCE = new MemoreStore();

    private Map<Long, User> users = new ConcurrentHashMap<>();
    private AtomicLong idGen = new AtomicLong(1);
    private MemoreStore() {


    }

    public static MemoreStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        user.setId(this.idGen.getAndIncrement());
        this.users.put(user.getId(), user);
    }

    @Override
    public void update(long id, String name, String login, String email, String password, String role, long addressId) {
        this.users.computeIfPresent(id, (key, value) -> {
                    if (name != null) {
                        value.setName(name);
                    }
                    if (login != null) {
                        value.setLogin(login);
                    }
                    if (email != null) {
                        value.setEmail(email);
                    }
                    return value;
                }
        );

    }

    @Override
    public void delete(long id) {
        this.users.remove(id);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<User>(this.users.values());
    }

    @Override
    public User findById(long id) {
        return this.users.get(id);
    }

    @Override
    public boolean contains(long id) {
        return this.users.containsKey(id);
    }

    @Override
    public User signin(String login, String password) {
        return null;
    }

    @Override
    public void addAddress(String city, String country) {
    }

    @Override
    public List<Address> allAddresses() {
        return null;
    }

    @Override
    public Address addressById(long id) {
        return null;
    }

    @Override
    public boolean containsAddress(String city, String country) {
        return false;
    }
}
