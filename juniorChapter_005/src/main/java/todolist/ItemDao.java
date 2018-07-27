package todolist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
/**
 * class ItemDao.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 26.07.2018
 */
public class ItemDao {
    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public void create(Item item) {
        Session session = this.openTransactSession();
        session.save(item);
        this.closeTransactSession(session);
    }

    public void update(Item item) {
        Session session = this.openTransactSession();
        session.update(item);
        this.closeTransactSession(session);
    }

    public void delete(Item item) {
        Session session = this.openTransactSession();
        session.delete(item);
        this.closeTransactSession(session);
    }

    public Item getById(long id) {
        Item result = null;
        Session session = this.openTransactSession();
        result = session.get(Item.class,  id);
        this.closeTransactSession(session);
        return result;
    }

    public List<Item> listAll() {
        List<Item> result = new ArrayList<>();
        Session session = this.factory.openSession();
        String query = "from Item";
        result = session.createQuery(query).list();
        session.close();

        return result;
    }

    public List<Item> listIncomplete() {
        List<Item> result = new ArrayList<>();
        Session session = this.factory.openSession();
        String query = "from Item where done = false ";
        result = session.createQuery(query).list();
        session.close();

        return result;
    }

    private Session openTransactSession() {
        Session session = this.factory.openSession();
        session.beginTransaction();
        return session;
    }

    private void closeTransactSession(Session session) {
        session.getTransaction().commit();
        session.close();
    }

}
