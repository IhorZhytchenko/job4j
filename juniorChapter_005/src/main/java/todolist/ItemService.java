package todolist;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
/**
 * class ItemService.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 26.07.2018
 */
public class ItemService {
    private static final ItemService INSTANCE = new ItemService();
    private final ItemDao itemDao = new ItemDao();

    private ItemService(){}

    
    public void add(String desc) {
        Item item = new Item();
        item.setDesc(desc);
        item.setDone(false);
        item.setCreated(new Timestamp(new Date().getTime()));
        this.itemDao.create(item);
    }

    public void update(long id, boolean done) {
        Item item = this.itemDao.getById(id);
        item.setDone(done);
        this.itemDao.update(item);
    }
    public List<Item> findAll() {
        return this.itemDao.listAll();
    }
    public List<Item> findIncomplete() {
        return this.itemDao.listIncomplete();
    }

    public static ItemService getINSTANCE() {
        return INSTANCE;
    }
}
