package kontrol;

import kontrol.bid.Action;
import kontrol.bid.Bid;
import kontrol.bid.Type;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
/**
 * Test.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $2$
 * @since 16.04.2018
 */
public class BooksTest {
    @Test
    public void addShow() {
        Books books = new Books();
        books.add(new Bid("Id1", "TTT", Type.add, Action.bid, 40, 10));
        books.add(new Bid("Id2", "TTT", Type.add, Action.ask, 45, 5));
        books.add(new Bid("Id3", "TTT", Type.add, Action.ask, 50, 15));
        books.add(new Bid("Id4", "TTT", Type.add, Action.ask, 60, 15));
        books.add(new Bid("Id5", "TTT", Type.add, Action.ask, 70, 15));
        books.add(new Bid("Id5", "TTT", Type.add, Action.bid, 55, 20));
        books.add(new Bid("Id6", "TTT", Type.add, Action.ask, 80, 15));
        books.add(new Bid("Id7", "TTT", Type.add, Action.ask, 90, 15));
        books.add(new Bid("Id8", "TTT", Type.add, Action.bid, 100, 10));
        books.add(new Bid("Id9", "TTT", Type.add, Action.bid, 110, 10));
        books.add(new Bid("Id10", "TTT", Type.add, Action.bid, 120, 10));
        books.add(new Bid("Id11", "TTT", Type.delete, Action.bid, 90, 24));
        books.add(new Bid("Id12", "AAA", Type.add, Action.bid, 90, 24));
        books.add(new Bid("Id13", "AAA", Type.add, Action.bid, 44, 24));
        books.show();
    }
    @Test
    public void booksSize() {
        Books books = new Books();
        books.add(new Bid("Id1", "TTT", Type.add, Action.bid, 40, 10));
        assertThat(books.getBooks().size(), is(1));
        books.add(new Bid("Id2", "A", Type.add, Action.bid, 40, 10));
        books.add(new Bid("Id3", "B", Type.add, Action.bid, 40, 10));
        assertThat(books.getBooks().size(), is(3));
        books.add(new Bid("Id4", "B", Type.add, Action.bid, 60, 10));
        assertThat(books.getBooks().size(), is(3));

    }
    @Test
    public void getValueFoPrice() {
        Books books = new Books();
        books.add(new Bid("Id1", "TTT", Type.add, Action.bid, 40, 10));
        assertThat(books.getBookValue("TTT",40), is("sell - 10"));
        books.add(new Bid("Id2", "TTT", Type.add, Action.bid, 40, 15));
        assertThat(books.getBookValue("TTT",40), is("sell - 25"));
        books.add(new Bid("Id3", "TTT", Type.add, Action.ask, 50, 35));
        assertThat(books.getBookValue("TTT",40), is("not found"));
        assertThat(books.getBookValue("TTT",50), is("buy - 10"));

    }
}