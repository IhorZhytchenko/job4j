package kontrol;

import kontrol.bid.Action;
import kontrol.bid.Bid;
import kontrol.bid.Type;

import java.util.*;
/**
 * Books.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $2$
 * @since 26.04.2018
 */
public class Books {
    private Map<String, Book> books;

    public Books() {
        this.books = new HashMap<>();
    }
    /**
     * Adds a bid to the glass.
     *
     * Depending on the variable bid.Type, calls the addAdd(bid) or addDelete(bid) methods
     *
     * @param bid data to be added to the glass
     */
    public void add(Bid bid) {
        if (bid.getType() == Type.add) {
            this.addAdd(bid);
        } else if (bid.getType() == Type.delete) {
            this.addDelete(bid);
        }
    }
    /**
     * Outputs information to the console.
     *
     */
    public void show() {
        for (Book book: books.values()) {
            System.out.println(book);
        }
    }

    public String getBookValue(String name, int price) {
        String result;
        if (this.books.containsKey(name)) {
            result = this.books.get(name).getValue(price);
        } else {
            result = "not found";
        }
        return result;
    }

    private void addDelete(Bid bid) {
        if (this.books.containsKey(bid.getBook())) {
            this.books.get(bid.getBook()).delete(bid);
        }

    }

    private void addAdd(Bid bid) {
        if (this.books.containsKey(bid.getBook())) {
            if (bid.getAction() == Action.bid) {
               this.books.get(bid.getBook()).addAddBid(bid, getComporator());
            } else  if (bid.getAction() == Action.ask) {
                this.books.get(bid.getBook()).addAddAsk(bid);
            }

        } else {
            this.addNew(bid);
        }
    }

    private void addNew(Bid bid) {
        Book book = new Book(bid.getBook(), this.getComporator());
        book.addInNew(bid);
        this.books.put(bid.getBook(), book);
    }

    private Comparator<Integer> getComporator() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        };
        return comparator;
    }

    public Map<String, Book> getBooks() {
        return books;
    }
}
