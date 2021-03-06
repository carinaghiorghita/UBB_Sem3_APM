package Model.ADTs;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;

public class mySemaphoreTable implements ISemaphoreTable {
    private HashMap<Integer,Pair<Integer, List<Integer>>> semTab;
    private int freeLocation = 0;

    public mySemaphoreTable() {
        semTab = new HashMap<Integer,Pair<Integer, List<Integer>>>();
    }
    public mySemaphoreTable(HashMap<Integer, Pair<Integer, List<Integer>>> st){semTab=st;}

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Integer key:semTab.keySet())
            s.append(key.toString()+"->"+semTab.get(key).toString()+" ");
        return s.toString();
    }

    @Override
    public void put(Integer ind, Pair<Integer, List<Integer>> tup) {
        semTab.put(ind,tup);
    }


    @Override
    public boolean lookup(Integer id) {
        return semTab.containsKey(id);
    }

    @Override
    public Pair<Integer, List<Integer>> get(Integer id) {
        return semTab.get(id);
    }

    @Override
    public HashMap<Integer, Pair<Integer, List<Integer>>> getMap() {
        return semTab;
    }

    @Override
    public int getFreeAddress(){
        freeLocation++;
        return freeLocation;
    }
    @Override
    public void update(int key,Pair<Integer, List<Integer>> value){
        semTab.replace(key,value);
    }

}
