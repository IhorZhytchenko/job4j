package map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * SimpleHashMap.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 17.04.2018
 */
public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Entry<K, V>> {

    private static final int DEFAULT_CAPACITY = 100;
    private static final double MAX_LOAD = 0.75;
    private Entry<K, V> [] table;
    private int size = 0;
    private int modCount = 0;

    public boolean insert(K key, V value) {
        if (this.table == null) {
            this.table = (Entry<K, V>[]) new Entry[DEFAULT_CAPACITY];
        }
        double load = this.size / this.table.length;
        if (load >= MAX_LOAD) {
            rehash();
        }
        int hash = key.hashCode();
        int index = Math.abs(hash % this.table.length);
        boolean result = false;
        if (this.table[index] == null) {
            this.table[index] = new Entry<>(hash, key, value);
            this.size++;
            this.modCount++;
            result = true;
        }
        return result;
    }

    public V get(K key) {
        if (this.table == null) {
            return null;
        }
        V result = null;
        int hash = key.hashCode();
        int index = Math.abs(hash % this.table.length);
        if (this.table[index] != null) {
            if (this.table[index].hash == hash && key.equals(this.table[index].key)) {
                result = this.table[index].getValue();
            }
        }
        return result;
    }

    public boolean delete(K key) {
        if (this.table == null) {
            return false;
        }
        boolean result = false;
        int hash = key.hashCode();
        int index = Math.abs(hash % this.table.length);
        if (this.table[index] != null) {
            if (this.table[index].hash == hash && key.equals(this.table[index].key)) {
                this.table[index] = null;
                result = true;
                this.modCount++;
                this.size--;
            }
        }
        return result;
    }


    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Entry<K, V>> {
        int cursor = -1;
        int expectedModCount = SimpleHashMap.this.modCount;

        @Override
        public boolean hasNext() {
            if (this.cursor == -1) {
                this.cursor = findElement();
            }

            return cursor != SimpleHashMap.this.table.length;
        }

        @Override
        public Entry<K, V> next() {
            if (this.expectedModCount != SimpleHashMap.this.modCount) {
                throw new ConcurrentModificationException();
            }
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            Entry<K, V> result = SimpleHashMap.this.table[cursor];
            this.cursor = findElement();
            return result;
        }

        private int findElement() {
            int result = SimpleHashMap.this.table.length;
            for (int i = (this.cursor + 1); i < SimpleHashMap.this.table.length; i++) {
                if (SimpleHashMap.this.table[i] != null) {
                    result = i;
                    break;
                }
            }
            return result;
        }
    }

    public  static class Entry<K, V> {
      final int hash;
      final K key;
      V value;


      Entry(int hash, K key, V value) {
          this.hash = hash;
          this.key = key;
          this.value = value;

      }

      public final K getKey() {
          return key;
      }

      public final V getValue() {
          return value;
      }
    }

    private void rehash() {
        final Entry<K, V>[] old = this.table;
        int capacity = this.table.length * 2;
        this.table = (Entry<K, V>[]) new Entry[capacity];
        for (int i = 0; i < old.length; i++) {
            if (old[i] != null) {
                Entry<K, V> entry =  old[i];
                int index = Math.abs(entry.hash % capacity);
                this.table[index] = entry;
            }
        }
    }
}
