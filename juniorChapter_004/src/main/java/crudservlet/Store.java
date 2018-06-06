package crudservlet;

import java.util.List;

/**
 * interface Store.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 28.05.2018
 */
public interface Store {

     void add(User user);
     void update(long id, String name, String login, String email, String password, String role);
     void delete(long id);
     List<User> findAll();
     User findById(long id);
     boolean contains(long id);
     User signin(String login, String password);
}
