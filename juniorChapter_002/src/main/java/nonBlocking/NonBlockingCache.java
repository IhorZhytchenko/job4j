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
    private ConcurrentHashMap<Integer,Entry<V>> cache = new ConcurrentHashMap<>();
    private AtomicInteger nextIndex = new AtomicInteger(0);

    public Entry<V> add(V value) {
        final int index = this.nextIndex.getAndIncrement();
        Entry<V> entry = new Entry<>(index, value);
        this.cache.put(index, entry);
        return entry;
    }

    public void delete(Entry<V> data) {
        this.cache.remove(data.getIndex());
    }

    public void update(Entry<V> data) throws OplimisticException {
        this.cache.computeIfPresent(data.getIndex(), (key, oldValue) -> {
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
        int index ;
        V value;

        public Entry(int index, V value) {
            this.index = index;
            this.value = value;
        }

        public int getVersion() {
            return this.version;
        }

        public int getIndex() {
            return this.index;
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
