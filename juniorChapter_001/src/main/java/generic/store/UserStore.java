package generic.store;
/**
 * UserStore.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 09.04.2018
 */
public class UserStore extends AbstractStore<User> {
    protected UserStore(int size) {
        super(size);
    }
}
