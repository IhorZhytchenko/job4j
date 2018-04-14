package set;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
/**
 * Test.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 14.04.2018
 */
public class SimpleHashSetTest {
    @Test()
    public void addContainsRemove() {
        SimpleHashSet<String> set = new SimpleHashSet<String>();
        assertThat(set.add("AAAAAAAAAAAAAAaaaaaaaaaaaa"), is(true));
        assertThat(set.add("12345"), is(true));
        assertThat(set.add("BBBBBBBBBBBBBB"), is(true));
        assertThat(set.add("12345"), is(false));
        assertThat(set.add("BBBBBBBBBBBBBB"), is(false));
        assertThat(set.add("ABCDabcd"), is(true));

        assertThat(set.contains("ABCDabcd"), is(true));
        assertThat(set.contains("ABCD"), is(false));
        assertThat(set.contains("1111111111111111"), is(false));
        assertThat(set.contains("12345"), is(true));
        assertThat(set.contains("12345"), is(true));

        assertThat(set.remove("12345"), is(true));
        assertThat(set.remove("12345"), is(false));
        assertThat(set.remove("12345"), is(false));
        assertThat(set.remove("1123456789"), is(false));
        assertThat(set.remove("asdfasdfasdf"), is(false));
        assertThat(set.remove("ABCDabcd"), is(true));

        assertThat(set.contains("12345"), is(false));
        assertThat(set.contains("ABCDabcd"), is(false));


    }

}