package com.simontran.collections.set;

public interface Set<K> {
    boolean contains(K key);

    void add(K key);

    void remove(K key);
}
