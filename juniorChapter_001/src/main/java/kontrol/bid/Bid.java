package kontrol.bid;
/**
 * Bid.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 26.04.2018
 */
public class Bid {
    private String id;
    private String book;
    private Type type;
    private Action action;
    private int price;
    private int volume;

    public Bid(String id, String book, Type type, Action action, int price, int volume) {
        this.id = id;
        this.book = book;
        this.type = type;
        this.action = action;
        this.price = price;
        this.volume = volume;
    }

    public String getId() {
        return this.id;
    }

    public String getBook() {
        return this.book;
    }

    public Type getType() {
        return this.type;
    }

    public Action getAction() {
        return this.action;
    }

    public int getPrice() {
        return this.price;
    }

    public int getVolume() {
        return this.volume;
    }
}
