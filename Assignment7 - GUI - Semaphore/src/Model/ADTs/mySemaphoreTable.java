package Model.ADTs;

import java.util.HashMap;
import java.util.List;

public class mySemaphoreTable implements ISemaphoreTable {
    private HashMap<Integer,ITuple<Integer, List<Integer>,Integer>> semTab;
    private int freeLocation = 0;

    public mySemaphoreTable() {
        semTab = new HashMap<Integer,ITuple<Integer, List<Integer>,Integer>>();
    }
    public mySemaphoreTable(HashMap<Integer,ITuple<Integer, List<Integer>,Integer>> st){semTab=st;}

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Integer key:semTab.keySet())
            s.append(key.toString()+"->"+semTab.get(key).toString()+" ");
        return s.toString();
    }

    @Override
    public void put(Integer ind, ITuple<Integer, List<Integer>, Integer> tup) {
        semTab.put(ind,tup);
    }


    @Override
    public boolean lookup(Integer id) {
        return semTab.containsKey(id);
    }

    @Override
    public ITuple<Integer, List<Integer>, Integer> get(Integer id) {
        return semTab.get(id);
    }

    @Override
    public HashMap<Integer, ITuple<Integer, List<Integer>, Integer>> getMap() {
        return semTab;
    }

    @Override
    public int getFreeAddress(){
        freeLocation++;
        return freeLocation;
    }
    @Override
    public void update(int key,ITuple<Integer, List<Integer>,Integer> value){
        semTab.replace(key,value);
    }

}
