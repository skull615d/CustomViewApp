package collections;

import java.util.*;
import java.util.function.Consumer;

public class MArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private E[] items;

    private int size;

    public MArrayList() {
        size = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public MArrayList(Collection<? extends E> c) {
        ensureCapacity(c.size() * 2 + 1);
        addAll(c);
    }

    public MArrayList(int capacity) {
        ensureCapacity(capacity);
    }

    public MArrayList(E[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        int max = size;
        if (max == -1)
            return "[]";
        StringBuilder result = new StringBuilder();
        result.append("[");
        int i = 0;
        while (true) {
            result.append(items[i]);
            i++;
            if (i == max)
                return result.append("]").toString();
            result.append(", ");
        }
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity(int capacity) {
        if (items == null)
            items = (E[]) new Object[capacity];
        else {
            E[] newItems = (E[]) new Object[capacity];
            System.arraycopy(items, 0, newItems, 0, size - 1);
            items = newItems;
        }
    }

    private void checkBounds(int index) {
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException();
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
        for (E item : items) {
            if (item.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        System.arraycopy(items, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean add(E e) {
        add(size(), e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        final Object[] es = items;
        int i = found(o, es);
        fastRemove(es, i);
        return i != -1;
    }

    private int found(Object o, Object[] es) {
        final int size = this.size;
        int i = 0;
        if (o == null) {
            for (; i < size; i++) {
                if (es[i] == null)
                    return i;
            }
        } else {
            for (; i < size; i++) {
                if (o.equals(es[i]))
                    return i;
            }
        }
        return -1;
    }

    private void fastRemove(Object[] es, int i) {
        final int newSize = size - 1;
        if ((newSize) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        es[size = newSize] = null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!this.contains(e))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E e : c) {
            this.add(e);
            result = true;
        }
        return result;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        boolean result = false;
        for (E e : c) {
            this.add(index++, e);
            result = true;
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean result = false;
        Iterator<?> it = iterator();
        while (it.hasNext()) {
            if (c.contains(it.next())) {
                it.remove();
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean result = false;
        Iterator<?> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                result = true;
            }
        }
        return result;
    }

    @Override
    public void clear() {
        final Object[] es = items;
        for (int to = size, i = size = 0; i < to; i++) {
            es[i] = null;
        }
    }

    @Override
    public E get(int index) {
        checkBounds(index);
        return items[index];
    }

    @Override
    public E set(int index, E element) {
        checkBounds(index);
        E oldItem = items[index];
        items[index] = element;
        return oldItem;
    }

    @Override
    public void add(int index, E element) {
        checkBounds(index);
        if (items.length == size()) ensureCapacity(size() * 2 + 1);
        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        checkBounds(index);
        E removeItem = items[index];
        System.arraycopy(items, index + 1, items, index, size - index);
        size--;
        return removeItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        checkBounds(index);
        return new MListIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        if (toIndex > size)
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        MArrayList<E> subList = new MArrayList<>();
        subList.addAll(Arrays.asList(items).subList(fromIndex, toIndex));
        return subList;
    }

    private class MIterator implements Iterator<E> {

        int currentPos;
        int lastRet = -1;

        MIterator() {
        }

        public boolean hasNext() {
            return currentPos != size;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            int i = currentPos;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] items = MArrayList.this.items;
            if (i >= items.length)
                throw new ConcurrentModificationException();
            currentPos = i + 1;
            return (E) items[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            try {
                MArrayList.this.remove(lastRet);
                currentPos = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Iterator.super.forEachRemaining(action);
        }
    }

    private class MListIterator extends MIterator implements ListIterator<E> {

        MListIterator(int index) {
            currentPos = index;
        }

        @Override
        public boolean hasPrevious() {
            return currentPos != 0;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E previous() {
            int i = currentPos - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Object[] items = MArrayList.this.items;
            if (i > items.length)
                throw new ConcurrentModificationException();
            currentPos = i;
            return (E) items[i];
        }

        @Override
        public int nextIndex() {
            return currentPos;
        }

        @Override
        public int previousIndex() {
            return currentPos - 1;
        }

        @Override
        public void set(E e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            try {
                MArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void add(E e) {
            try {
                int i = currentPos;
                MArrayList.this.add(i, e);
                currentPos = i + 1;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
