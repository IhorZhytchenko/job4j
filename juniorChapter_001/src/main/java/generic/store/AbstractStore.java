package generic.store;

import generic.SimpleList;
/**
 * AbstractStore.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 09.04.2018
 */
public abstract  class AbstractStore<T extends Base> implements Store<T> {
    protected SimpleList<T> list;

    protected AbstractStore(int size) {
        this.list = new SimpleList<>(size);
    }

    @Override
    public void add(T model) {
        this.list.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getId().equals(id)) {
                this.list.set(i, model);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getId().equals(id)) {
                this.list.delete(i);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getId().equals(id)) {
                result = this.list.get(i);
                break;
            }
        }
        if (result == null) {
            throw new IndexOutOfBoundsException();
        }
        return result;

    }
}
