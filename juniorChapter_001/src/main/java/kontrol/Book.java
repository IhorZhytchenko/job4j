package kontrol;

import kontrol.bid.Action;
import kontrol.bid.Bid;

import java.util.*;

/**
 * Book.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $2$
 * @since 26.04.2018
 */
public class Book {
    private String name;
    private Map<Integer, Integer> sell;
    private Map<Integer, Integer> buy;

    public Book(String name, Comparator<Integer> comparator) {
        this.name = name;
        this.sell = new TreeMap<>(comparator);
        this.buy = new TreeMap<>(comparator);

    }

    public void addInNew(Bid bid) {
        if (bid.getAction() == Action.bid) {
            this.sell.put(bid.getPrice(), bid.getVolume());
        } else  if (bid.getAction() == Action.ask) {
            this.buy.put(bid.getPrice(), bid.getVolume());
        }
    }

    public void addAddBid(Bid bid, Comparator<Integer> comparator) {
        int price = bid.getPrice();
        int value = bid.getVolume();
        final Set<Integer> priceSet = new TreeSet<>(comparator);
        priceSet.addAll(this.buy.keySet());
        for (Integer p : priceSet) {
            if (p >= price) {
                int key = p;
                if (this.buy.get(key) > value) {
                    this.buy.put(key, this.buy.get(key) - value);
                    value = 0;
                    break;
                } else if (this.buy.get(key) == value) {
                    this.buy.remove(key);
                    value = 0;
                    break;
                } else {
                    value = value - this.buy.get(key);
                    this.buy.remove(key);
                }

            } else {
                break;
            }
        }
        if (value > 0) {
            if (this.sell.containsKey(price)) {
                this.sell.put(price, (this.sell.get(price) + value));
            } else {
                this.sell.put(price, value);
            }
        }

    }

    public void addAddAsk(Bid bid) {
        int price = bid.getPrice();
        int value = bid.getVolume();
        final List<Integer> priceList = new ArrayList<>(this.sell.keySet());
        for (int i = priceList.size() - 1; i >= 0; i--) {
            if (priceList.get(i) <= price) {
                int key = priceList.get(i);
                if (this.sell.get(key) > value) {
                    this.sell.put(key, this.sell.get(key) - value);
                    value = 0;
                    break;
                } else if (this.sell.get(key) == value) {
                    this.sell.remove(key);
                    value = 0;
                    break;
                } else {
                    value = value - this.sell.get(key);
                    this.sell.remove(key);
                }

            } else {
                break;
            }
        }
        if (value > 0) {
            if (this.buy.containsKey(price)) {
                this.buy.put(price, (this.buy.get(price) + value));
            } else {
                this.buy.put(price, value);
            }
        }
    }

    public void delete(Bid bid) {
        Map<Integer, Integer> book;
        if (bid.getAction() == Action.bid) {
            book = this.buy;
        } else if (bid.getAction() == Action.ask) {
            book = this.sell;
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(this.name);
        result.append("\n");
        result.append("Продажа   Цена    Покупка\n");
        for (Map.Entry<Integer, Integer> entry: sell.entrySet()) {
            result.append(entry.getValue() + "        " + entry.getKey() + "\n");
        }
        for (Map.Entry<Integer, Integer> entry: buy.entrySet()) {
            result.append("          " + entry.getKey() + "      " + entry.getValue() + "\n");
        }
        return result.toString();
    }

    public String getValue(int price) {
        String result;
        if (this.buy.containsKey(price)) {
            result = "buy - " + this.buy.get(price);
        } else if (this.sell.containsKey(price)) {
            result = "sell - " + this.sell.get(price);
        } else {
            result = "not found";
        }
        return result;
    }
}
