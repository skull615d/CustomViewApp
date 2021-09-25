package collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MLinkedList<E> implements List<E> {

    private int size = 0;
    private Node<E> first;
    private Node<E> last;

    public MLinkedList() {
    }

    Node<E> node(int index) {
        Node<E> x;
        // size >> 1 - catchy size / 2
        if (index < (size >> 1)) {
            x = first;
            for (int i = 0; i < index; i++)
                x = x.nextItem;
        } else {
            x = last;
            for (int i = size - 1; i > index; i--)
                x = x.previousItem;
        }
        return x;
    }

    private void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.nextItem = newNode;
        size++;
    }

    private void linkBefore(E element, Node<E> node) {
        final Node<E> previousItem = node.previousItem;
        final Node<E> newNode = new Node<>(previousItem, element, node);
        node.previousItem = newNode;
        if (previousItem == null)
            first = newNode;
        else
            previousItem.nextItem = newNode;
        size++;
    }

    private E unlink(Node<E> node) {
        final E item = node.item;
        final Node<E> nextItem = node.nextItem;
        final Node<E> previousItem = node.previousItem;

        if (previousItem == null) {
            first = nextItem;
        } else {
            previousItem.nextItem = nextItem;
            node.previousItem = null;
        }

        if (nextItem == null) {
            last = previousItem;
        } else {
            nextItem.previousItem = previousItem;
            node.nextItem = null;
        }

        node.item = null;
        size--;
        return item;
    }

    private void checkBounds(int index) {
        if (index >= 0 && index < size)
            throw new IndexOutOfBoundsException(index);
    }

    @Override
    public String toString() {
        int max = size - 1;
        if (max == -1)
            return "[]";
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; ; i++) {
            result.append(node(i).item);
            if (i == max)
                return result.append("]").toString();
            result.append(", ");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> f = first; f != null; f = f.nextItem) {
                if (f.item == null) {
                    unlink(f);
                    return true;
                }
            }
        } else {
            for (Node<E> f = first; f != null; f = f.nextItem) {
                if (o.equals(f.item)) {
                    unlink(f);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void clear() {
        Node<E> node = first;
        while (node != null) {
            Node<E> nextItem = node.nextItem;
            node.item = null;
            node.previousItem = null;
            node.nextItem = null;
            node = nextItem;
        }
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        checkBounds(index);
        return node(index).item;
    }

    @Override
    public E set(int index, E element) {
        checkBounds(index);
        Node<E> node = node(index);
        E old = node.item;
        node.item = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        checkBounds(index);
        if (index == size)
            linkLast(element);
        else
            linkBefore(element, node(index));
    }

    @Override
    public E remove(int index) {
        checkBounds(index);
        return unlink(node(index));
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> node = first; node != null; node = node.nextItem) {
                if (node.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> node = first; node != null; node = node.nextItem) {
                if (o.equals(node.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    private static class Node<E> {
        E item;
        Node<E> nextItem;
        Node<E> previousItem;

        Node(Node<E> previousItem, E item, Node<E> nextItem) {
            this.item = item;
            this.previousItem = previousItem;
            this.nextItem = nextItem;
        }
    }
}
