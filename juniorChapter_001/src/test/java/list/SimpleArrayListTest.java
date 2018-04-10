package list;

import org.junit.Test;


import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayListTest {
    SimpleArrayList<Integer> list;

    @Test(expected = IndexOutOfBoundsException.class)
    public void getAdd() {
        list = new SimpleArrayList<Integer>(0);
        for (int i = 0; i < 200; i++) {
            list.add(i);
        }
        assertThat(list.get(0), is(0));
        assertThat(list.get(1), is(1));
        assertThat(list.get(100), is(100));
        assertThat(list.get(199), is(199));
        list.get(200);

    }
    @Test(expected = ConcurrentModificationException.class)
    public void iterator() {
        list = new SimpleArrayList<Integer>();
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
        list.add(5);
        it.next();



    }
}