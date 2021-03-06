package Model.ADTs;

import java.util.HashMap;

public class myLockTable implements ILockTable{
    private HashMap<Integer, Integer> heapTable;
    private int freeLocation = 0;

    public myLockTable() {
        heapTable = new HashMap<Integer, Integer>();
    }

    public myLockTable(HashMap<Integer, Integer> heap) {
        heapTable=heap;
    }

    @Override
    public int getFreeAddress(){
        freeLocation++;
        return freeLocation;
    }

    @Override
    public void put(int key,int value){
        heapTable.put(key,value);
    }

    @Override
    public boolean containsKey(int key){
        return heapTable.containsKey(key);
    }

    @Override
    public int get(int key){
        return heapTable.get(key);
    }

    @Override
    public void update(int key,int value){
        heapTable.replace(key,value);
    }

    @Override
    public HashMap<Integer, Integer> getLockTable(){
        return heapTable;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Integer key:heapTable.keySet())
            s.append(key.toString()+"->"+heapTable.get(key).toString()+" ");
        return s.toString();
    }

}
