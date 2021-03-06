package Model.ADTs;

import java.util.HashMap;
import java.util.Map;

public interface IHeap<V> {
    void put(int key,V value);
    V get(int key);
    boolean containsKey(int key);
    int getFreeAddress();
    void update(int key,V value);
    void setFreeAddress(int freeAddress);
    HashMap<Integer, V> getHeap();
    void setHeap(HashMap<Integer, V> heap);
    String toString();
}
