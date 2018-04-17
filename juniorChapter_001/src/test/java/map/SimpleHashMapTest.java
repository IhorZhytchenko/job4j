package map;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
/**
 * Test.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 17.04.2018
 */
public class SimpleHashMapTest {

    @Test
    public void insertGetDelete() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        assertThat(map.insert("Id1", 1), is(true));
        assertThat(map.insert("Id2", 2), is(true));
        assertThat(map.insert("Id3", 3), is(true));
        assertThat(map.insert("Id4", 4), is(true));
        assertThat(map.insert("Id3", 3), is(false));
        assertThat(map.get("Id1"), is(1));
        assertThat(map.get("Id1"), is(1));
        assertThat(map.get("Id2"), is(2));
        assertThat(map.get("Id3"), is(3));
        assertThat(map.get("Id4"), is(4));
        assertThat(map.delete("Id1"), is(true));
        assertThat(map.delete("Id1"), is(false));
        assertThat(map.delete("Iqqqq"), is(false));

    }
    @Test
    public void iterator() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("Id1", 1);
        map.insert("Id2", 2);
        map.insert("Id3", 3);
        Iterator<SimpleHashMap.Entry<String, Integer>> iterator = map.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        SimpleHashMap.Entry<String, Integer> entry1 = iterator.next();
        SimpleHashMap.Entry<String, Integer> entry2 = iterator.next();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        SimpleHashMap.Entry<String, Integer> entry3 = iterator.next();
        assertThat(iterator.hasNext(), is(false));
        assertThat(iterator.hasNext(), is(false));
        System.out.println(entry1.getKey() + " - " + entry1.getValue());
        System.out.println(entry2.getKey() + " - " + entry2.getValue());
        System.out.println(entry3.getKey() + " - " + entry3.getValue());




    }

}