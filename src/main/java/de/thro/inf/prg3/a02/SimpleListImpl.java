package de.thro.inf.prg3.a02;

import java.util.NoSuchElementException;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable {

    private Element head = null;
    private Element last = null;
    private int count = 0;

    @Override
    public void add(Object o) {
        if (head == null) {
            head = last = new Element(o);
        }
        else {
            last.next = new Element(o);
            last = last.next;
        }

        count++;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {
        SimpleList filteredList = new SimpleListImpl();

        this.forEach(o -> {
            if (filter.include(o))
                filteredList.add(o);
        });

        return filteredList;
    }

    @Override
    public java.util.Iterator iterator() {
        return new Iterator();
    }

    private static class Element {
        private Object item;
        private Element next;

        Element(Object item) {
            this.item = item;
        }
    }

    class Iterator implements java.util.Iterator {

        private Element cursor;

        public Iterator() {
            cursor = SimpleListImpl.this.head;
        }

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public Object next() {
            if(this.hasNext()) {
                Object current = cursor.item;
                cursor = cursor.next;
                return current;
            }
            throw new NoSuchElementException();
        }
    }
}
