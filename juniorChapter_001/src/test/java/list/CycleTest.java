package list;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
/**
 * Test.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 11.04.2018
 */
public class CycleTest {

    @Test()
    public void hasCycleTrue() {
        Cycle cycle = new Cycle();
        Cycle.Node<Integer> node1 = cycle.new Node<Integer>();
        Cycle.Node<Integer> node2 = cycle.new Node<Integer>();
        Cycle.Node<Integer> node3 = cycle.new Node<Integer>();
        Cycle.Node<Integer> node4 = cycle.new Node<Integer>();
        Cycle.Node<Integer> node5 = cycle.new Node<Integer>();
        Cycle.Node<Integer> node6 = cycle.new Node<Integer>();
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node3;
        assertThat(cycle.hasCycle(node1), is(true));
        assertThat(cycle.hasCycle(node3), is(true));
        assertThat(cycle.hasCycle(node4), is(true));
        assertThat(cycle.hasCycle(node6), is(true));
    }

        @Test()
        public void hasCycleFalse() {
            Cycle cycle = new Cycle();
            Cycle.Node<Integer> node1 = cycle.new Node<Integer>();
            Cycle.Node<Integer> node2 = cycle.new Node<Integer>();
            Cycle.Node<Integer> node3 = cycle.new Node<Integer>();
            Cycle.Node<Integer> node4 = cycle.new Node<Integer>();
            Cycle.Node<Integer> node5 = cycle.new Node<Integer>();
            Cycle.Node<Integer> node6 = cycle.new Node<Integer>();
            node1.next = node2;
            node2.next = node3;
            node3.next = node4;
            node4.next = node5;
            node5.next = node6;
            assertThat(cycle.hasCycle(node1), is(false));
            assertThat(cycle.hasCycle(node3), is(false));
            assertThat(cycle.hasCycle(node4), is(false));
            assertThat(cycle.hasCycle(node6), is(false));



    }

}