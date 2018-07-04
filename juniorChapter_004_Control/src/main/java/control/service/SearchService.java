package control.service;

import control.repository.entity.User;

import java.util.List;
/**
 * interface SearchService.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 30.06.2018
 */
public interface SearchService {
     RepositoryService service = RepositoryService.getInstance();

    List<User> search(String condition);
}
