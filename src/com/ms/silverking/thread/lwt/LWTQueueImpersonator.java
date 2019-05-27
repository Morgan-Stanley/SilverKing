package com.ms.silverking.thread.lwt;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import com.ms.silverking.log.Log;

/**
 * Makes a Worker look like a BlockingQueue for legacy code that submits
 * work via queues. Only work submission is supported via this "queue".
 *
 * @param <T>
 */
public final class LWTQueueImpersonator<T> implements BlockingQueue<T> {
    private final BaseWorker<T> worker;
    private final int            priority;
    
    public LWTQueueImpersonator(BaseWorker<T> worker, int priority) {
        this.worker = worker;
        this.priority = priority;
    }
    
    public LWTQueueImpersonator(BaseWorker<T> worker) {
        this(worker, LWTPoolImpl.defaultPriority);
    }
    
    @Override
    public boolean add(T e) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public int drainTo(Collection<? super T> c, int maxElements) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public int drainTo(Collection<? super T> c) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public boolean offer(T e, long timeout, TimeUnit unit)
            throws InterruptedException {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public boolean offer(T e) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void put(T e) throws InterruptedException {
        if (Log.levelMet(Level.FINE)) {
            Log.fine("LWTQueueImpersonator.put ", e);
        }
        worker.addPrioritizedWork(e, priority);
    }

    public void putAll(Collection<? extends T> c) throws InterruptedException {
        for (T t : c) {
            put(t);
        }
    }
    
    @Override
    public T poll(long timeout, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException("Method not implemented");
    }
    
    @Override
    public T take() throws InterruptedException {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public int remainingCapacity() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Method not implemented");
    }
    
    @Override
    public T element() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public T peek() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public T poll() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public T remove() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        try {
            putAll(c);
        } catch (InterruptedException ie) {
        }
        return true;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
