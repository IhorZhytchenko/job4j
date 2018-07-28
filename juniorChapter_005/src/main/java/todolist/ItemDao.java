package todolist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * class ItemDao.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $2$
 * @since 28.07.2018
 */
public class ItemDao {
    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    private <T> T tx(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            tx.commit();
            session.close();
        }
    }
    public void create(Item item) {
        this.tx(session -> session.save(item));

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
        return this.tx(session -> session.get(Item.class,  id));
    }

    public List<Item> listAll() {
        List<Item> result = this.tx(session -> session.createQuery("from Item").list());
        return result;
    }

    public List<Item> listIncomplete() {
        List<Item> result = this.tx(session -> session.createQuery("from Item where done = false ").list());
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
