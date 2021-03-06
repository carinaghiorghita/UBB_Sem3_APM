package Model.ADTs;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;

public class myBarrierTable implements IBarrierTable{
    private HashMap<Integer, Pair<Integer, List<Integer>>> barrierTable;
    private int freeLocation = 0;

    public myBarrierTable() {
        barrierTable = new HashMap<Integer, Pair<Integer, List<Integer>>>();
    }

    public myBarrierTable(HashMap<Integer, Pair<Integer, List<Integer>>> heap) {
        barrierTable=heap;
    }

    @Override
    public void put(int key,Pair<Integer, List<Integer>> value){
        barrierTable.put(key,value);
    }

    @Override
    public Pair<Integer, List<Integer>> get(int key){
        return barrierTable.get(key);
    }

    @Override
    public boolean containsKey(int key){
        return barrierTable.containsKey(key);
    }

    @Override
    public int getFreeAddress(){
        freeLocation++;
        return freeLocation;
    }

    @Override
    public void update(int key,Pair<Integer, List<Integer>> value){
        barrierTable.replace(key,value);
    }

    @Override
    public void setFreeAddress(int free){
        freeLocation=free;
    }

    @Override
    public HashMap<Integer, Pair<Integer, List<Integer>>> getBarrierTable(){
        return barrierTable;
    }

    @Override
    public void setBarrierTable(HashMap<Integer, Pair<Integer, List<Integer>>> heap){
        barrierTable=heap;
    }



    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Integer key:barrierTable.keySet())
            s.append(key.toString()+"->"+barrierTable.get(key).toString()+" ");
        return s.toString();
    }

}
