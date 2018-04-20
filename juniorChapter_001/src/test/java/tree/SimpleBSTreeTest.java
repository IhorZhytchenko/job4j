package tree;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
/**
 * Test.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 20.04.2018
 */
public class SimpleBSTreeTest {

    @Test
    public void addIterator() {
        SimpleBSTree<Integer> tree = new SimpleBSTree<>();
        tree.add(25);
        tree.add(30);
        tree.add(15);
        tree.add(41);
        tree.add(35);
        tree.add(7);
        tree.add(25);

        Iterator<Integer> it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(15));
        assertThat(it.next(), is(25));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(25));
        assertThat(it.next(), is(30));
        assertThat(it.next(), is(35));
        assertThat(it.next(), is(41));
        assertThat(it.hasNext(), is(false));
        assertThat(it.hasNext(), is(false));


    }

}