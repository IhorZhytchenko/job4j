package kontrol;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
/**
 * Book.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
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

    public String getName() {
        return this.name;
    }

    public Map<Integer, Integer> getSell() {
        return this.sell;
    }

    public Map<Integer, Integer> getBuy() {
        return this.buy;
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
}
