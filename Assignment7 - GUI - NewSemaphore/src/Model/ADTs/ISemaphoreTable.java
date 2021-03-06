package Model.ADTs;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;

public interface ISemaphoreTable {
    String toString();
    void put(Integer ind, Pair<Integer, List<Integer>> tup);
    boolean lookup(Integer id);
    Pair<Integer,List<Integer>> get(Integer id);
    HashMap<Integer,Pair<Integer, List<Integer>>> getMap();
    int getFreeAddress();
    void update(int key,Pair<Integer, List<Integer>> value);

}
