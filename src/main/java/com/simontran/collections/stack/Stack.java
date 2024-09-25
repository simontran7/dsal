package com.simontran.collections.stack;

public interface Stack<E> {
    E top();

    void push(E element);

    E pop();
}
