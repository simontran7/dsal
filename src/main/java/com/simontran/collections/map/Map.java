package com.simontran.collections.map;

public interface Map<K, V> {
    V get(K key);

    void add(K key, V value);

    void remove(K key);
}
