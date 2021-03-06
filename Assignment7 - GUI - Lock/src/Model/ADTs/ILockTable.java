package Model.ADTs;

import java.util.HashMap;

public interface ILockTable {
    int getFreeAddress();
    void put(int key,int value);
    boolean containsKey(int key);
    int get(int key);
    void update(int key,int value);
    HashMap<Integer, Integer> getLockTable();
    String toString();

}
