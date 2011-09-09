package eu.javaspecialists.course.master.memory.exercise431;

import java.lang.ref.*;
import java.util.*;
import java.util.concurrent.*;

public class FailFastCollection<E> implements Collection<E> {
    private final Collection<E> delegate;

    private final Map<WeakReference<Iterator<E>>, Object> iterators =
            new ConcurrentHashMap<WeakReference<Iterator<E>>, Object>();

    public FailFastCollection(Collection<E> delegate) {
        this.delegate = delegate;
    }

    private void checkForCurrentIteration() {
        // check whether we have any cleared weak references as keys in
        // the iterators map.  If we do, remove them.  Otherwise, force a
        // full GC (System.gc()) and then check again for cleared weak
        // references.  If we still have uncleared weak references to
        // iterators, throw a ConcurrentModificationException
        boolean unclearedIterator = false;
        for (WeakReference<Iterator<E>> ref : iterators.keySet()) {
            Iterator<E> iter = ref.get();
            if (iter != null) {
                unclearedIterator = true;
                break;
            }
        }
        if (unclearedIterator) {
            System.gc();
            for (WeakReference<Iterator<E>> ref : iterators.keySet()) {
                if (ref.get() == null) {
                    iterators.remove(ref);
                }
            }

            if (iterators.size()  > 0){
                throw new ConcurrentModificationException();
            }

        }
    }

    // Non-modifying safe operations
    public int size() {
        return delegate.size();
    }

    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    public boolean contains(Object o) {
        return delegate.contains(o);
    }

    public Object[] toArray() {
        return delegate.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return delegate.toArray(a);
    }

    public boolean containsAll(Collection<?> c) {
        return delegate.containsAll(c);
    }

    public boolean equals(Object o) {
        return delegate.equals(o);
    }

    public int hashCode() {
        return delegate.hashCode();
    }

    public String toString() {
        return delegate.toString();
    }

    // Operations that might modify the underlying collection - unsafe
    public boolean add(E e) {
        checkForCurrentIteration();
        return delegate.add(e);
    }

    public boolean remove(Object o) {
        checkForCurrentIteration();
        return delegate.remove(o);
    }

    public boolean addAll(Collection<? extends E> c) {
        checkForCurrentIteration();
        return delegate.addAll(c);
    }

    public boolean removeAll(Collection<?> c) {
        checkForCurrentIteration();
        return delegate.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        checkForCurrentIteration();
        return delegate.retainAll(c);
    }

    public void clear() {
        checkForCurrentIteration();
        delegate.clear();
    }

    public Iterator<E> iterator() {
        return new NoFailIterator(delegate.iterator());
    }

    private class NoFailIterator implements Iterator<E> {
        private final Iterator<E> delegate;
        private final WeakReference<Iterator<E>> reference;

        private NoFailIterator(Iterator<E> delegate) {
            this.delegate = delegate;
            reference = new WeakReference<Iterator<E>>(this);
            iterators.put(reference, "dummy");
        }

        public boolean hasNext() {
            if (delegate.hasNext()) {
                return true;
            } else {
                iterators.remove(reference);
                return false;
            }
        }

        public E next() {
            E e = delegate.next();
            if (!delegate.hasNext()) {
                iterators.remove(reference);
            }
            return e;
        }

        public void remove() {
            delegate.remove();
        }
    }
}

