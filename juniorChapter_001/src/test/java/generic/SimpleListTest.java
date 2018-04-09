package generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
/**
 * SimpleListTest.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 09.04.2018
 */
public class SimpleListTest {
    SimpleList<Integer> list;

    @Before
    public void setUp() {
        list = new SimpleList<Integer>(10);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
    }

    @Test
    public void getAddSetDelete() {
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
        assertThat(list.get(3), is(4));
        assertThat(list.get(4), is(5));
        list.set(3, 55);
        assertThat(list.get(3), is(55));
        list.delete(3);
        assertThat(list.get(3), is(5));
        list.add(88);
        assertThat(list.get(4), is(88));
        list.delete(0);
        assertThat(list.get(0), is(2));
    }

    @Test
    public void iterable() {
        list = new SimpleList<Integer>(4);
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
        list.add(4);
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));

    }

}