package kontrol;

import kontrol.bid.Action;
import kontrol.bid.Bid;
import kontrol.bid.Type;

import java.util.*;
/**
 * Books.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
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

    private void addDelete(Bid bid) {
        if (this.books.containsKey(bid.getBook())) {
            Map<Integer, Integer> book;
            if (bid.getAction() == Action.bid) {
                book = this.books.get(bid.getBook()).getBuy();
            } else if (bid.getAction() == Action.ask) {
                book = this.books.get(bid.getBook()).getSell();
            } else {
                return;
            }
            int key = bid.getPrice();
            if (book.containsKey(key)) {
               int volume = bid.getVolume();
               if (book.get(key) > volume) {
                   book.put(key, book.get(key) - volume);
               } else {
                   book.remove(key);
               }
            }
        }

    }

    private void addAdd(Bid bid) {
        if (this.books.containsKey(bid.getBook())) {
            if (bid.getAction() == Action.bid) {
               addAddBid(bid);
            } else  if (bid.getAction() == Action.ask) {
                addAddAsk(bid);
            }

        } else {
            this.addNew(bid);
        }
    }

    private void addAddAsk(Bid bid) {
        Map<Integer, Integer> buy = this.books.get(bid.getBook()).getBuy();
        Map<Integer, Integer> sell = this.books.get(bid.getBook()).getSell();
        int price = bid.getPrice();
        int value = bid.getVolume();
        final List<Integer> priceList = new ArrayList<>(sell.keySet());
        for (int i = priceList.size() - 1; i >= 0; i--) {
            if (priceList.get(i) <= price) {
                int key = priceList.get(i);
                if (sell.get(key) > value) {
                    sell.put(key, sell.get(key) - value);
                    value = 0;
                    break;
                } else if (sell.get(key) == value) {
                    sell.remove(key);
                    value = 0;
                    break;
                } else {
                    value = value - sell.get(key);
                    sell.remove(key);
                }

            } else {
                break;
            }
        }
        if (value > 0) {
            if (buy.containsKey(price)) {
                buy.put(price, (buy.get(price) + value));
            } else {
                buy.put(price, value);
            }
        }
    }

    private void addAddBid(Bid bid) {
        Map<Integer, Integer> buy = this.books.get(bid.getBook()).getBuy();
        Map<Integer, Integer> sell = this.books.get(bid.getBook()).getSell();
        int price = bid.getPrice();
        int value = bid.getVolume();
        final Set<Integer> priceSet = new TreeSet<>(getComporator());
        priceSet.addAll(buy.keySet());
        for (Integer p : priceSet) {
            if (p >= price) {
                int key = p;
                if (buy.get(key) > value) {
                    buy.put(key, buy.get(key) - value);
                    value = 0;
                    break;
                } else if (buy.get(key) == value) {
                    buy.remove(key);
                    value = 0;
                    break;
                } else {
                    value = value - buy.get(key);
                    buy.remove(key);
                }

            } else {
                break;
            }
        }
        if (value > 0) {
            if (sell.containsKey(price)) {
                sell.put(price, (sell.get(price) + value));
            } else {
                sell.put(price, value);
            }
        }

    }

    private void addNew(Bid bid) {
        Book book = new Book(bid.getBook(), this.getComporator());
        if (bid.getAction() == Action.bid) {
            book.getSell().put(bid.getPrice(), bid.getVolume());
        } else  if (bid.getAction() == Action.ask) {
            book.getBuy().put(bid.getPrice(), bid.getVolume());
        }
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
