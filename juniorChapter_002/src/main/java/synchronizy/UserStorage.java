package synchronizy;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

/**
 * UserStorage.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 28.04.2018
 */
@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private List<User> storage = new ArrayList<>();


    public boolean add (User user) {
        synchronized (this) {
            return this.storage.add(user);
        }
    }

    public  boolean update(User user) {
        synchronized (this) {
            int index = this.storage.indexOf(user);
            if (index == -1) {
                return false;
            } else {
                this.storage.get(index).setAmount(user.getAmount());
                return true;
            }

        }
    }

    public boolean delete(User user) {
        synchronized (this) {
            return this.storage.remove(user);
        }
    }

    public boolean transfer(int fromId, int toId, int amount) {
        synchronized (this) {
            boolean result;
            int fromIndex = this.storage.indexOf(new User(fromId,0));
            int toIndex = this.storage.indexOf(new User(toId,0));
            if ((fromIndex != -1) && (toIndex != -1)) {
                int fromAmount = this.storage.get(fromIndex).getAmount() - amount;
                int toAmount = this.storage.get(toIndex).getAmount() + amount;
                this.storage.get(fromIndex).setAmount(fromAmount);
                this.storage.get(toIndex).setAmount(toAmount);
                result = true;
            } else {
                result = false;
            }
            return result;
        }
    }

    public List<User> getStorage() {
        return storage;
    }
}
