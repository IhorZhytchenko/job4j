package nonBlocking;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * class NonBlockingCache.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 03.05.2018
 */
public class NonBlockingCache<V> {
    private ConcurrentHashMap<V,Entry<V>> cache = new ConcurrentHashMap<>();


    public Entry<V> add(V value) {
        Entry<V> entry = new Entry<>( value);
        Entry<V> putEntry = this.cache.putIfAbsent(value, entry);
        return putEntry == null ? entry : putEntry;
    }

    public void delete(Entry<V> data) {
        this.cache.remove(data.getValue());
    }

    public void update(Entry<V> data) throws OplimisticException {
        this.cache.computeIfPresent(data.getValue(), (key, oldValue) -> {
            int oldVersion = oldValue.getVersion();
            if (oldVersion  != data.getVersion()) {
                throw new OplimisticException();
            } else {
                data.incrementVersion();
                return data;
            }
        });
    }

    public  static class Entry<V> {
        int version = 0;
        V value;

        public Entry(V value) {

            this.value = value;
        }

        public int getVersion() {
            return this.version;
        }

        public V getValue() {
            return this.value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public void incrementVersion() {
            this.version++;
        }

    }

}
