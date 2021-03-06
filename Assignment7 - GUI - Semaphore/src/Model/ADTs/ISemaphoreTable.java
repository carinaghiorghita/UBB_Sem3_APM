package Model.ADTs;

import java.util.HashMap;
import java.util.List;

public interface ISemaphoreTable {
    String toString();
    void put(Integer ind, ITuple<Integer, List<Integer>,Integer> tup);
    boolean lookup(Integer id);
    ITuple<Integer,List<Integer>,Integer> get(Integer id);
    HashMap<Integer,ITuple<Integer, List<Integer>,Integer>> getMap();
    int getFreeAddress();
    void update(int key,ITuple<Integer, List<Integer>,Integer> value);

}
