package tree;

import java.util.*;
/**
 * SimpleBSTree.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 20.04.2018
 */
public class SimpleBSTree<E extends Comparable<E>> implements Iterable<E> {
    private Node<E> root;
    private int modCount = 0;

    public void add(E e) {
        if (this.root == null) {
            this.root = new Node<>(e);
        } else {
            addNode(root, e);
        }
    }

    private void addNode(Node<E> node, E e) {
        int cmp = e.compareTo(node.value);
        if (cmp <= 0) {
            if (node.left != null) {
                this.addNode(node.left, e);
            } else {
                node.left = new Node<>(e);
                this.modCount++;
            }
        } else {
            if (node.right != null) {
                this.addNode(node.right, e);
            } else {
                node.right = new Node<>(e);
                this.modCount++;
            }
        }

    }


    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        private Queue<Node<E>> data;
        private int expectedModCount = SimpleBSTree.this.modCount;


        @Override
        public boolean hasNext() {
            if (this.data == null) {
                this.initData();
            }
            return this.data.size() != 0;
        }

        @Override
        public E next() {
            if (this.expectedModCount != SimpleBSTree.this.modCount) {
                throw new ConcurrentModificationException();
            }
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            return this.data.poll().value;
        }

        private void initData() {
            this.data = new LinkedList<>();
            Node<E> node = SimpleBSTree.this.root;
            if (node != null) {
                this.traverse(node);
            }
        }

        private void traverse(Node<E> node) {
            if (node.left != null) {
                this.traverse(node.left);
            }
            this.data.offer(node);

            if (node.right != null) {
                this.traverse(node.right);
            }
        }

    }
    private static class Node<E> {
        E value;
        Node<E> left;
        Node<E> right;

        public Node(E value) {
            this.value = value;
        }
    }
}
