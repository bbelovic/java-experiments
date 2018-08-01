package net.bbelovic.javaexperiments;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static java.lang.String.format;

public class ValueHolder<K, V> {
    private final ConcurrentMap<K, V> holderMap = new ConcurrentHashMap<>();

    public int size() {
        return holderMap.size();
    }

    void put(K k, V v) {
        holderMap.put(k, v);
    }

    @Override
    public String toString() {
        return format("ValueHolder[val=%s]", holderMap);
    }
}
