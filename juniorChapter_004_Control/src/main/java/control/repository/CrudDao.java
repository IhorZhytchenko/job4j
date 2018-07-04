package control.repository;

import java.util.List;
/**
 * interface CrudDao.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 29.06.2018
 */
public interface CrudDao<T> {
    List<T> findAll();
    T add(T object);
    void update(T object);
    void delete(long id);
    T findById(long id);
}
