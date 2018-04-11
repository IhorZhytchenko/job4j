package list;

import org.junit.Test;

import java.util.NoSuchElementException;

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
public class SimpleStackTest {

    @Test()
    public void pushPoll() {
        SimpleStack<String> stack = new SimpleStack<String>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        assertThat(stack.poll(), is("c"));
        assertThat(stack.poll(), is("b"));
        stack.push("d");
        assertThat(stack.poll(), is("d"));
        assertThat(stack.poll(), is("a"));


    }

}