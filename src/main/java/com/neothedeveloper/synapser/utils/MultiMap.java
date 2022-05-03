package com.neothedeveloper.synapser.utils;

import java.util.ArrayList;
import java.util.List;

public class MultiMap<T, K> {
    private final List<T> m_keys;
    private final List<K> m_values;
    public MultiMap() {
        this.m_keys = new ArrayList<>();
        this.m_values = new ArrayList<>();
    }

    public void add(T key, K value) {
        this.m_keys.add(key);
        this.m_values.add(value);
    }

    public void removeByKey(T key) {
        for (int i = 0; i < m_keys.size(); i++) {
            if (m_keys.get(i) == m_keys) {
                K v = m_values.get(i);
                T k = m_keys.get(i);
                this.m_values.remove(v);
                this.m_keys.remove(k);
            }
        }
    }

    public void removeValue(K value) {
        for (int i = 0; i < m_values.size(); i++) {
            if (m_values.get(i) == value) {
                this.m_values.remove(i);
                this.m_keys.remove(i);
                break;
            }
        }
    }

    public List<K> getMultiple(T key) {
        List<K> out = new ArrayList<>();
        for (int i = 0; i < m_keys.size(); i++) {
            if (m_keys.get(i) == key) {
                out.add(m_values.get(i));
            }
        }
        return out;
    }
}
