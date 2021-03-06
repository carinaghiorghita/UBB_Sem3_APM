package Model.ADTs;

import java.util.HashMap;

public interface ILatchTable {
    void put(int key,int value);
    int get(int key);
    boolean containsKey(int key);
    int getFreeAddress();
    void update(int key,int value);
    void setFreeAddress(int freeAddress);
    HashMap<Integer, Integer> getLatchTable();
    void setLatchTable(HashMap<Integer, Integer> table);
    String toString();

}
