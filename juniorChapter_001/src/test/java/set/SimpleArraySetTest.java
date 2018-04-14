package set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArraySetTest {

    @Test()
    public void addIterator() {
        SimpleArraySet<String> set = new SimpleArraySet<String>();
        set.add("a1");
        set.add("a2");
        set.add("a1");
        set.add("a3");
        set.add("a1");
        set.add("a4");
        set.add("a4");
        Iterator<String> it = set.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("a1"));
        assertThat(it.next(), is("a2"));
        assertThat(it.next(), is("a3"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("a4"));
        assertThat(it.hasNext(), is(false));
        assertThat(it.hasNext(), is(false));
    }
}