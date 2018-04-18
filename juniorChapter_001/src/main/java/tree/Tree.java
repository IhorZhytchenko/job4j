package tree;

import java.util.*;
/**
 * Tree.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 18.04.2018
 */
class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private final Node<E> root;
    private int modCount = 0;

    public Tree(final E value) {
        this.root = new Node<>(value);
    }

    public boolean isBinary() {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        boolean result = true;
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.leaves().size() > 2) {
               result = false;
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> par = this.findBy(parent);
        boolean result = false;
       if (par.isPresent()) {
           if (this.identityCheck(par.get(), child)) {
               par.get().add(new Node<>(child));
               this.modCount++;
               result = true;
           }
       }
        return result;
    }

    private boolean identityCheck(Node<E> parent, E child) {
        boolean result = true;
        for (Node<E> ch : parent.leaves()) {
           if (ch.equals(child)) {
               result = false;
               break;
           }
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        private Queue<Node<E>> data = new LinkedList<>();
        private int expectedModCount = Tree.this.modCount;

        Itr() {
            this.data.offer(Tree.this.root);
        }

        @Override
        public boolean hasNext() {
            return this.data.size() != 0;
        }

        @Override
        public E next() {
            if (this.expectedModCount != Tree.this.modCount) {
                throw new ConcurrentModificationException();
            }
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            return getElement();
        }

        private E getElement() {
            Node<E> node = this.data.poll();
            for (Node<E> child : node.leaves()) {
                this.data.offer(child);
            }
            return node.getValue();
        }
    }

}