package tree;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import static org.junit.Assert.*;
/**
 * Test.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 18.04.2018
 */
public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<Integer>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<Integer>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void iterator() {
        Tree<Integer> tree = new Tree<>(5);
        tree.add(5, 10);
        tree.add(10, 100);
        tree.add(5, 20);
        Iterator<Integer> it = tree.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(10));
        assertThat(it.next(), is(20));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(100));
        assertThat(it.hasNext(), is(false));
        assertThat(it.hasNext(), is(false));

    }

    @Test
    public void isBinary() {
        Tree<Integer> tree = new Tree<Integer>(1);
        tree.add(1, 2);
        tree.add(1, 4);
        tree.add(4, 8);
        tree.add(2, 16);
        assertThat(tree.isBinary(), is(true));
        tree.add(1, 20);
        assertThat(tree.isBinary(), is(false));

    }
}