package set;
/**
 * SimpleLinkedSet.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 14.04.2018
 */
public class SimpleHashSet<E> {
    private static final int DEFAULT_CAPACITY = 100;
    private static final double MAX_LOAD = 0.75;
    private Entry<E> [] table;
    private int size;

    public SimpleHashSet() {
        this.table = (Entry<E>[]) new Entry[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Adds the specified element to this set if it is not already present.
     *
     * @param e element to be added to this set
     * @return true if this set did not already contain the specified
     * element
     */
    public boolean add(E e) {
        double load = this.size / this.table.length;
        if (load >= MAX_LOAD) {
            rehash();
        }
        int hash = e.hashCode();
        int index = Math.abs(hash % this.table.length);
        boolean result = false;
        if (!containsIndex(index, hash, e)) {
            this.table[index] = new Entry<E>(hash, e);
            result = true;
            this.size++;
        }
       return result;
    }
    /**
     * Returns true if this set contains the specified element.
     *
     * @param e element whose presence in this set is to be tested
     * @return true if this set contains the specified element
     */
    public boolean contains(E e) {
        int hash = e.hashCode();
        int index = Math.abs(hash % this.table.length);

        return containsIndex(index, hash, e);
    }

    /**
     * Removes the specified element from this set if it is present.
     *
     * @param e object to be removed from this set, if present
     * @return true if the set contained the specified element
     */
    public boolean remove(E e) {
        int hash = e.hashCode();
        int index = Math.abs(hash % this.table.length);
        boolean result = false;
        if (containsIndex(index, hash, e)) {
            this.table[index] = null;
            result = true;
            this.size--;
        }
        return result;
    }

    private void rehash() {
        final Entry<?>[] old = this.table;
        int capacity = this.table.length * 2;
        this.table = (Entry<E>[]) new Entry[capacity];
        for (int i = 0; i < old.length; i++) {
            if (old[i] != null) {
                Entry<E> entry = (Entry<E>) old[i];
                int index = Math.abs(entry.hash % capacity);
                this.table[index] = entry;
            }
        }
    }

    private boolean containsIndex(int index, int hash, E e) {
        boolean result = false;
        if (this.table[index] != null) {
            if ((this.table[index].hash == hash) && this.table[index].key.equals(e)) {
                result = true;
            }
        }
        return result;
    }
    private static class Entry<E> {
        final int hash;
        final E key;

         Entry(int hash, E key) {
            this.hash = hash;
            this.key = key;

        }
    }
}
