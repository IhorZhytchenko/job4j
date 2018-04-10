package generic.store;

import generic.SimpleList;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
/**
 * UserStoreTest.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 09.04.2018
 */
public class UserStoreTest {
    UserStore store;
    User user1;
    User user2;
    User user3;
    User user4;
    User user5;

    @Before
    public void setUp() {
       store = new UserStore(5);
       user1 = new User("1a");
       user2 = new User("2a");
       user3 = new User("3a");
       user4 = new User("4a");
       user5 = new User("5a");

       store.add(user1);
       store.add(user2);
       store.add(user3);
       store.add(user4);
       store.add(user5);
    }

    @Test
    public void replaceDeleteFindById() {
        assertThat(store.findById("1a"), is(user1));
        assertThat(store.findById("2a"), is(user2));
        assertThat(store.findById("5a"), is(user5));
        assertThat(store.delete("5a"), is(true));
        assertThat(store.delete("5a4"), is(false));
        assertThat(store.replace("4a", new User("6a")), is(true));
        assertThat(store.replace("4a", new User("6a")), is(false));
        assertThat(store.replace("4a", new User("6a")), is(false));


    }

}