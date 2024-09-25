package com.simontran.collections.priorityqueue;

public interface PriorityQueue<K extends Comparable<K>> {
    K top();

    void add(K key);

    K removeTop();
}
