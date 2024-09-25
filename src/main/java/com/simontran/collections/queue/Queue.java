package com.simontran.collections.queue;

public interface Queue<E> {
    E front();

    void enqueue(E element);

    E dequeue();
}
